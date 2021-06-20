package ru.nsu.kudryavtsev.andrey.gameObject.player;

import ru.nsu.kudryavtsev.andrey.model.GameConfig;

public class HumanPlayer implements Player
{
    private final int ID;
    private final int[] coords = new int[2];
    private boolean isAlive;
    private int ammo = GameConfig.MAX_AMMO;
    private int score = 0;

    public HumanPlayer(int ID, int x, int y)
    {
        this.ID = ID;
        isAlive = true;
        coords[0] = x;
        coords[1] = y;
    }

    @Override
    public boolean isAlive()
    {
        return isAlive;
    }

    @Override
    public void kill()
    {
        isAlive = false;
    }

    @Override
    public int getCoordinate(int i)
    {
        if (i < 0 || i > 1) throw new IllegalArgumentException("Несуществующая координата");
        return coords[i];
    }

    @Override
    public void setCoordinate(int i, int value)
    {
        if (i < 0 || i > 1) throw new IllegalArgumentException("Несуществующая координата");
        coords[i] = value;
    }

    @Override
    public int getID()
    {
        return ID;
    }

    @Override
    public int getAmmo()
    {
        return ammo;
    }

    @Override
    public void decAmmo()
    {
        ammo--;
    }

    @Override
    public void incAmmo()
    {
        ammo++;
    }

    @Override
    public int getScore()
    {
        return score;
    }

    @Override
    public void addScore(int value)
    {
        score += value;
    }

    @Override
    public long getTimeLeftForAction()
    {
        return 1;
    }

    @Override
    public void reduceTimeLeftForAction(long time)
    {

    }

    @Override
    public void restartTimeLeftForAction()
    {

    }
}
