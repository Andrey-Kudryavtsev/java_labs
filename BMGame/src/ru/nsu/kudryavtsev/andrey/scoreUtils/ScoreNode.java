package ru.nsu.kudryavtsev.andrey.scoreUtils;

public class ScoreNode
{
    private final String username;
    private final int score;

    public ScoreNode(String username, int score)
    {
        this.username = username;
        this.score = score;
    }

    public String getUsername()
    {
        return username;
    }

    public int getScore()
    {
        return score;
    }

    @Override
    public String toString()
    {
        return (username + " " + score);
    }
}
