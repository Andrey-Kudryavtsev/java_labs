package ru.nsu.kudryavtsev.andrey.gameObject.player;

import ru.nsu.kudryavtsev.andrey.model.GameConfig;

public class AIPlayer implements Player
{
    private final int ID;
    private final int[] coords = new int[2];
    private boolean isAlive;
    private int ammo = GameConfig.MAX_AMMO;
    private long timeLeftForAction;

    public AIPlayer(int ID, int x, int y)
    {
        this.ID = ID;
        isAlive = true;
        coords[0] = x;
        coords[1] = y;
        timeLeftForAction = 0;
    }

    @Override
    public int getID()
    {
        return ID;
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
    public long getTimeLeftForAction()
    {
        return timeLeftForAction;
    }

    @Override
    public void reduceTimeLeftForAction(long time)
    {
        timeLeftForAction -= time;
    }

    @Override
    public void restartTimeLeftForAction()
    {
        timeLeftForAction = 400;
    }

    @Override
    public int getScore()
    {
        return 0;
    }

    @Override
    public void addScore(int value)
    {

    }
}
