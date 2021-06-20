package ru.nsu.kudryavtsev.andrey.gameObject.player;

public interface Player
{
    boolean isAlive();
    void kill();
    int getCoordinate(int i);
    void setCoordinate(int i, int value);
    int getID();
    int getAmmo();
    void decAmmo();
    void incAmmo();

    int getScore();
    void addScore(int value);

    long getTimeLeftForAction();
    void reduceTimeLeftForAction(long time);
    void restartTimeLeftForAction();
}
