package ru.nsu.kudryavtsev.andrey.gameObject.player;

public interface Bot
{
    long getTimeLeftForAction();
    void reduceTimeLeftForAction(long time);
    void restartTimeLeftForAction();
}
