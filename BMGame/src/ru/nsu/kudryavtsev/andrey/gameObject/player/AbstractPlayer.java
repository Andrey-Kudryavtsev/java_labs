package ru.nsu.kudryavtsev.andrey.gameObject.player;

public abstract class AbstractPlayer implements Player
{
    public enum PlayerType
    {
        HUMAN,
        AI;
    }
    protected static int nextID = 0;


//    protected static synchronized void incID()
//    {
//        ID++;
//    }

//    public static synchronized int getID()
//    {
//        return ID;
//    }
}
