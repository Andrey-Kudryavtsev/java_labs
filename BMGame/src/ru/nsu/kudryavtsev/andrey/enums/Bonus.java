package ru.nsu.kudryavtsev.andrey.enums;

public enum Bonus
{
    WALL_BROKEN(15),
    BOMB_PLACED(30),
    KILL(200),
    WIN(500);

    private final int points;

    Bonus(int points)
    {
        this.points = points;
    }

    public int points()
    {
        return points;
    }
}
