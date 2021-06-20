package ru.nsu.kudryavtsev.andrey.model;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import ru.nsu.kudryavtsev.andrey.commands.Task;
import ru.nsu.kudryavtsev.andrey.enums.Bonus;
import ru.nsu.kudryavtsev.andrey.enums.GameState;
import ru.nsu.kudryavtsev.andrey.enums.TileType;
import ru.nsu.kudryavtsev.andrey.field.DefaultField;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.gameObject.bomb.Bomb;
import ru.nsu.kudryavtsev.andrey.gameObject.player.Player;
import ru.nsu.kudryavtsev.andrey.gameObject.player.HumanPlayer;
import ru.nsu.kudryavtsev.andrey.gameObject.player.AIPlayer;
import ru.nsu.kudryavtsev.andrey.scoreUtils.ScoreComparator;
import ru.nsu.kudryavtsev.andrey.scoreUtils.ScoreNode;

public class GameModel implements Runnable
{
    private final CopyOnWriteArrayList<GameModelListener> listeners = new CopyOnWriteArrayList<>();
    private final Player[] players;
    private String username = null;
    private final ArrayList<Bomb> bombs;
    private Field field;
    private final Random rg;
    private final BlockingQueue<Task> userInputQueue;
    private final PriorityQueue<ScoreNode> highScores = new PriorityQueue<ScoreNode>(10, new ScoreComparator());
    private GameState gameState;
    private boolean isRunning;
    private int winnerID = -1;

    public GameModel()
    {
        loadHighScores();
        field = loadMap();
        players = new Player[GameConfig.MAX_PLAYERS];
        bombs = new ArrayList<>(GameConfig.MAX_PLAYERS * GameConfig.MAX_AMMO);
        rg = new Random();
        userInputQueue = new LinkedBlockingQueue<>();
        gameState = GameState.MENU;
        isRunning = true;
    }

    private void loadHighScores()
    {
        highScores.clear();
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new FileInputStream("highScores.txt"));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        while (scanner.hasNextLine())
        {
            String[] line = scanner.nextLine().split(" ");
            ScoreNode scoreNode = new ScoreNode(line[0], Integer.parseInt(line[1]));
            highScores.add(scoreNode);
        }
    }

    private void updateHighScores()
    {
        if (gameState == GameState.GAME_OVER)
            return;
        if (winnerID != 0) // если человек проиграл
            return;

        ScoreNode scoreNode = new ScoreNode(username, getScore(winnerID));
        Iterator<ScoreNode> iter1 = highScores.iterator();
        while (iter1.hasNext())
        {
            ScoreNode node = iter1.next();
            if (node.getUsername().equals(username))
                if (getScore(winnerID) > node.getScore())
                    iter1.remove();
                else
                    return;
        }
        highScores.add(scoreNode);

        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter("highScores.txt");
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        PriorityQueue<ScoreNode> highScoresCopy = new PriorityQueue<>(highScores);
        int border = (highScoresCopy.size() > GameConfig.SCORE_ENTRIES) ? highScoresCopy.size() - GameConfig.SCORE_ENTRIES : 0;
        while (highScoresCopy.size() != border)
        {
            writer.println(highScoresCopy.remove());
        }
        writer.close();
    }

    public PriorityQueue<ScoreNode> getHighScores()
    {
        return new PriorityQueue<>(highScores);
    }

    private Field loadMap()
    {
        Field field = null;
        try
        {
            field = new DefaultField("map1");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return field;
    }

    public Field getField()
    {
        return field;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public int getWinnerID()
    {
        return winnerID;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getLastAlive() // -1 - нет последнего выжившего, иначе - id последнего выжившего
    {
        int aliveID = -1;
        for (Player player : players)
        {
            if (player != null && player.isAlive())
            {
                if (aliveID == -1)
                    aliveID = player.getID();
                else
                    return -1;
            }
        }
        return aliveID;
    }

    public int alivePlayersLeft()
    {
        int amount = 0;
        for (Player player : players)
        {
            if (player.isAlive())
                amount++;
        }
        return amount;
    }

    public int getScore(int playerID)
    {
        return (players[playerID] != null) ? players[playerID].getScore() : 0;
    }

    public boolean isRunning()
    {
        return isRunning;
    }

    public synchronized void addListener(GameModelListener listener)
    {
        if (listener == null)
            throw new NullPointerException("Пустой аргумент");
        if (listeners.contains(listener))
            throw new IllegalArgumentException("Повторное добавление слушателя");
        listeners.add(listener);
        notifyListener(listener);
    }

    public synchronized void removeListener(GameModelListener listener)
    {
        if (listener == null)
            throw new NullPointerException("Пустой аргумент");
        if (!listeners.contains(listener))
            throw new IllegalArgumentException("Удаление несуществующего слушателя");
        listeners.remove(listener);
    }

    public synchronized void notifyListener(GameModelListener listener)
    {
        if (listener == null)
            throw new NullPointerException("Пустой аргумент");
        listener.gameModelChanged(this);
    }

    public synchronized void notifyListeners()
    {
        for (final GameModelListener listener : listeners)
            notifyListener(listener);
    }

    public void offerUserInput(Task task)
    {
        userInputQueue.offer(task);
    }

    public void toPlay()
    {
        if (gameState != GameState.MENU)
            return;
        restartGame();
        gameState = GameState.PLAY;
    }

    public void toAbout()
    {
        if (gameState != GameState.MENU)
            return;
        gameState = GameState.ABOUT;
    }

    public void toHighScores()
    {
        if (gameState != GameState.MENU)
            return;
        gameState = GameState.HIGH_SCORES;
    }

    public void toMenu()
    {
        if (gameState == GameState.MENU || gameState == GameState.PLAY || gameState == GameState.GAME_STOP)
            return;
        gameState = GameState.MENU;
    }

    @Override
    public void run()
    {
        startGameLoop();
        System.out.println("STOPPED");
    }

    public void startGameLoop()
    {
        while (isRunning)
        {
            long now = System.currentTimeMillis();
            notifyListeners();
            checkGameOver();
            handleInput();
            if (gameState == GameState.PLAY)
                updateGame(100);
            try
            {
                Thread.sleep(((System.currentTimeMillis() - now) >= 100) ? 0 : 100 - (System.currentTimeMillis() - now));
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        notifyListeners();

//        long then = System.currentTimeMillis();
//        while (isRunning)
//        {
//            long now = System.currentTimeMillis();
//            long timeElapsed = now - then;
//            handleInput();
//            if (gameState == GameState.PLAY)
//                updateGame(timeElapsed);
//            notifyListeners();
//            then = now;
//        }
    }

    public void stopGameLoop()
    {
        gameState = GameState.GAME_STOP;
        isRunning = false;
    }

    private void handleInput()
    {
        Task task;
        if ((task = userInputQueue.poll()) != null)
            task.command.execute(task.playerID, this, task.argList);
    }

    private void restartGame()
    {
        IDGiver.restart();
        winnerID = -1;
        username = null;
        loadHighScores();
        field = loadMap();
        bombs.clear();
        userInputQueue.clear();
        Arrays.fill(players, null);
        addHuman();
        addBot();
    }

    private void updateGame(long timeElapsed)
    {
        updateBombs(timeElapsed);
        updateAIPlayers(timeElapsed);
    }

    private void updateBombs(long timeElapsed)
    {
        ListIterator<Bomb> iter = bombs.listIterator();
        while(iter.hasNext())
        {
            Bomb bomb = iter.next();
            if (bomb.isActive())
            {
                bomb.reduceTimeLeftForExplosion(timeElapsed);
                if (bomb.getTimeLeftForExplosion() <= 0)
                    explodeBomb(bomb);
            } else
            {
                bomb.reduceTimeLeftForClear(timeElapsed);
                if (bomb.getTimeLeftForClear() <= 0)
                {
                    clearExplosion(bomb);
                    iter.remove();
                }
            }
        }
    }

    private void updateAIPlayers(long timeElapsed)
    {
        for (int ID = 0; ID < players.length; ++ID)
        {
            if (players[ID] != null)
            {
                if (players[ID].getTimeLeftForAction() <= 0)
                {
                    double whatToDO = rg.nextDouble();
                    if (whatToDO < 0.8)
                    {
                        int dx = 0;
                        int dy = 0;
                        double randomDir = rg.nextDouble();
                        if (randomDir >= 0 && randomDir < 0.35)
                        {
                            dy = -1;
                        } else if (randomDir >= 0.35 && randomDir < 0.5)
                        {
                            dx = 1;
                        } else if (randomDir >= 0.5 && randomDir < 0.65)
                        {
                            dy = 1;
                        } else if (randomDir >= 0.65 && randomDir < 1)
                        {
                            dx = -1;
                        }
                        movePlayer(ID, dx, dy);
                    } else
                    {
                        plantBomb(ID);
                    }
                    players[ID].restartTimeLeftForAction();
                } else
                    players[ID].reduceTimeLeftForAction(timeElapsed);
            }
        }
    }

    private void addHuman()
    {
        players[0] = new HumanPlayer(IDGiver.getFreeId(),0, 0);
        field.setTile(0, 0, TileType.PLAYER);
    }

    private void addBot()
    {
        for (int i = 1; i < players.length; ++i) // пропускаем игрока-человека
        {
            if (players[i] == null)
            {
                players[i] = new AIPlayer(IDGiver.getFreeId(), field.getWidth() - 1, field.getHeight() - 1); // TODO: временное решение с аргументами
                field.setTile(field.getWidth() - 1, field.getHeight() - 1, TileType.PLAYER);
                return;
            }
        }
    }

    private void checkGameOver()
    {
        if (gameState == GameState.PLAY)
        {
            switch (alivePlayersLeft())
            {
                case 0 -> {
                    winnerID = -1;
                    gameState = GameState.GAME_OVER;
                }
                case 1 -> {
                    System.out.println("GAME OVER");
                    winnerID = getLastAlive();
                    players[winnerID].addScore(Bonus.WIN.points());
                    updateHighScores();
                    gameState = GameState.GAME_OVER;
                }
            }
        }
    }

    public void movePlayer(int playerID, int dx, int dy)
    {
        if (gameState != GameState.PLAY)
            return;
        Player player = players[playerID];
        if (!player.isAlive())
            return;

        int oldX = player.getCoordinate(0);
        int oldY = player.getCoordinate(1);
        int newX = oldX + dx;
        int newY = oldY + dy;
        if (!field.inBounds(newX, newY))
            return;
        if (!field.getCurrTile(newX, newY).isPassable())
            return;
        if (field.getCurrTile(newX, newY).isDangerous())
        {
            player.kill();
            field.setTile(oldX, oldY, TileType.FLOOR);
            return;
        }
        field.returnTile(oldX, oldY);
        field.setTile(newX, newY, TileType.PLAYER);
        player.setCoordinate(0, newX);
        player.setCoordinate(1, newY);
    }

    public  void plantBomb(int playerID)
    {
        if (gameState != GameState.PLAY)
            return;
        Player player = players[playerID];
        if (player.getAmmo() == 0)
            return;
        if (!player.isAlive())
            return;

        int x = player.getCoordinate(0);
        int y = player.getCoordinate(1);
        if (!field.getPrevTile(x, y).isPassable())
            return;
        player.decAmmo();
        player.addScore(Bonus.BOMB_PLACED.points());
        bombs.add(new Bomb(player.getID(), x, y));
        field.setTile(x, y, TileType.BOMB);
        field.setTile(x, y, TileType.PLAYER);
    }

    public void explodeBomb(Bomb bomb)
    {
        bomb.deactivate(); // сразу деактивируем, чтобы цепная реакция не шла бесконечно

        int ownerID = bomb.getOwnerID();
        int x = bomb.getCoords(0);
        int y = bomb.getCoords(1);

        players[ownerID].incAmmo();
        int leftBound = Math.max(x - GameConfig.EXPLOSION_RADIUS, 0);
        int rightBound = Math.min(x + GameConfig.EXPLOSION_RADIUS, field.getWidth() - 1);
        int upBound = Math.max(y - GameConfig.EXPLOSION_RADIUS, 0);
        int bottomBound = Math.min(y + GameConfig.EXPLOSION_RADIUS, field.getHeight() - 1);

        for (int i = leftBound; i <= rightBound; ++i) // взрываем горизонтальную линию
        {
            for (Player player : players) // есть ли на клетке со взрывом игрок
            {
                if (player != null && player.getCoordinate(0) == i && player.getCoordinate(1) == y) // если на линии огня есть игрок, то убиваем его
                {
                    player.kill();
                    players[ownerID].addScore(Bonus.KILL.points()); // добавляем очки за убийство игроку, поставившему бомбу
                    break; // больше игроков на этой клетке не ищем
                }
            }
            for (Bomb otherBomb : bombs) // есть ли на клетке со взрывом бомба
            {
                if (otherBomb != bomb && otherBomb.isActive() && otherBomb.getCoords(0) == i && otherBomb.getCoords(1) == y)
                {
                    explodeBomb(otherBomb);
                    break;
                }
            }
            if (field.getCurrTile(i, y) == TileType.WALL)
                players[ownerID].addScore(Bonus.WALL_BROKEN.points());
            field.setTile(i, y, TileType.EXPLOSION);
        }
        for (int i = upBound; i <= bottomBound; ++i) // взрываем вертикальную линию
        {
            for (Player player : players)
            {
                if (player != null && player.getCoordinate(0) == x && player.getCoordinate(1) == i)
                {
                    player.kill();
                    players[ownerID].addScore(Bonus.KILL.points());
                    break;
                }
            }
            for (Bomb otherBomb : bombs) // есть ли на клетке со взрывом бомба
            {
                if (otherBomb != bomb && otherBomb.isActive() && otherBomb.getCoords(0) == x && otherBomb.getCoords(1) == i)
                {
                    explodeBomb(otherBomb);
                    break;
                }
            }
            if (field.getCurrTile(x, i) == TileType.WALL)
                players[ownerID].addScore(Bonus.WALL_BROKEN.points());
            if (i != y) // костыль, чтобы очистка происходила нормально
                field.setTile(x, i, TileType.EXPLOSION);
        }
    }

    public void clearExplosion(Bomb bomb)
    {
        int x = bomb.getCoords(0);
        int y = bomb.getCoords(1);

        int leftBound = Math.max(x - GameConfig.EXPLOSION_RADIUS, 0);
        int rightBound = Math.min(x + GameConfig.EXPLOSION_RADIUS, field.getWidth() - 1);
        int upBound = Math.max(y - GameConfig.EXPLOSION_RADIUS, 0);
        int bottomBound = Math.min(y + GameConfig.EXPLOSION_RADIUS, field.getHeight() - 1);

        for (int i = leftBound; i <= rightBound; ++i)
        {
            field.returnTile(i, y);
            if (field.getCurrTile(i, y).isExplosible())
                field.setTile(i, y, TileType.FLOOR);
        }
        for (int i = upBound; i <= bottomBound; ++i)
        {
            field.returnTile(x, i);
            if (field.getCurrTile(x, i).isExplosible())
                field.setTile(x, i, TileType.FLOOR);
        }
    }

    private static class IDGiver
    {
        private static int FREE_ID = 0;

        public static int getFreeId()
        {
            FREE_ID++;
            return FREE_ID - 1;
        }

        public static void restart()
        {
            FREE_ID = 0;
        }
    }
}
