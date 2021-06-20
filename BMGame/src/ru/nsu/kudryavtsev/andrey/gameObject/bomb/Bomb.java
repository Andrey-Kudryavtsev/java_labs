package ru.nsu.kudryavtsev.andrey.gameObject.bomb;

public class Bomb
{
    private final int ownerID;
    private long timeLeftForExplosion;
    private long timeLeftForClear;
    private final int[] coords = new int[2];
    private boolean isActive;

    public Bomb(int ownerID, int x, int y)
    {
        this.ownerID = ownerID;
        coords[0] = x;
        coords[1] = y;
        timeLeftForExplosion = 5000;
        timeLeftForClear = 1000;
        isActive = true;
    }

    public int getOwnerID()
    {
        return ownerID;
    }

    public long getTimeLeftForExplosion()
    {
        return timeLeftForExplosion;
    }

    public long getTimeLeftForClear()
    {
        return  timeLeftForClear;
    }

    public int getCoords(int i)
    {
        if (i != 0 && i != 1)
            throw new IllegalArgumentException();
        return coords[i];
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void deactivate()
    {
        isActive = false;
    }

    public void reduceTimeLeftForExplosion(long time)
    {
        timeLeftForExplosion -= time;
    }

    public void reduceTimeLeftForClear(long time)
    {
        timeLeftForClear -= time;
    }
}
