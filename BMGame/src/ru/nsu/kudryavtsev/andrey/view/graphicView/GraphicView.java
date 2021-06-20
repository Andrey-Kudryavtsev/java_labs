package ru.nsu.kudryavtsev.andrey.view.graphicView;

import ru.nsu.kudryavtsev.andrey.controller.GraphicController;
import ru.nsu.kudryavtsev.andrey.enums.GameState;
import ru.nsu.kudryavtsev.andrey.model.GameModel;
import ru.nsu.kudryavtsev.andrey.view.View;
import ru.nsu.kudryavtsev.andrey.view.graphicView.panels.*;

import javax.swing.*;

public class GraphicView extends JFrame implements View
{
    private final int ownerID;
    private int width = 800;
    private int height = 800;

    private final MenuPanel menuPanel;
    private final AboutPanel aboutPanel;
    private final GamePanel gamePanel;
    private final ScorePanel scorePanel;
    private final JButton menuButton;
    private final JButton setNameButton;

    public GraphicView(int ownerID, GraphicController controller)
    {
        this.ownerID = ownerID;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);
        setFocusable(true);
        addKeyListener(controller);
        menuPanel = new MenuPanel(controller);
        aboutPanel = new AboutPanel(controller);
        gamePanel = new GamePanel(controller);
        scorePanel = new ScorePanel(controller);

        menuButton = new JButton("MENU");
        menuButton.addActionListener(controller);

        setNameButton = new JButton("NAME");
        setNameButton.addActionListener(controller);
    }

    @Override
    public void draw(GameModel model)
    {
        GameState gameState = model.getGameState();

        switch (gameState)
        {
            case MENU -> {
                drawMenu(model);
            }
            case ABOUT -> {
                drawAbout(model);
            }
            case HIGH_SCORES -> {
                drawHighScores(model);
            }
            case PLAY -> {
                drawGame(model);
            }
            case GAME_OVER -> {
                drawGameOver(model);
            }
            case GAME_STOP -> {
                dispose();
            }
        }
    }

    @Override
    public void gameModelChanged(GameModel model)
    {
        draw(model);
    }

    private void drawMenu(GameModel model)
    {
        setContentPane(menuPanel);
        validate();
        repaint();
    }

    private void drawAbout(GameModel model)
    {
        setContentPane(aboutPanel);
        validate();
        repaint();
    }

    private void drawHighScores(GameModel model)
    {
        scorePanel.preparePanel(model.getHighScores());
        setContentPane(scorePanel);
        validate();
        repaint();
    }

    private void drawGame(GameModel model)
    {
        if (model.getUsername() == null)
        {
            String username = JOptionPane.showInputDialog(this, "Enter your name:");
            if (username == null) username = "Anon";
            setNameButton.setActionCommand("NAME " + username);
            setNameButton.doClick();
        }

        gamePanel.preparePanel(model.getField());
        setContentPane(gamePanel);
        validate();
        repaint();
    }

    private void drawGameOver(GameModel model)
    {
        int winnerID = model.getWinnerID();
        String info;
        if (winnerID == -1)
            info = "Draw\nReturn to main menu";
        else if (winnerID == ownerID)
            info = "Congratulations, " + model.getUsername() + ". You win! Score: " + model.getScore(winnerID) + "\nReturn to main menu";
        else
            info = "You lose, " + model.getUsername() + " :(\nReturn to main menu";
        JOptionPane.showMessageDialog(this, info);
        menuButton.doClick();
    }
}
