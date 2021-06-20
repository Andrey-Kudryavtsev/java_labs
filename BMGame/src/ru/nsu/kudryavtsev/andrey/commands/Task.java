package ru.nsu.kudryavtsev.andrey.commands;

public class Task // эту штуку можно передавать по сети
{
    public final int playerID;
    public final Command command;
    public final String[] argList;

    public Task(int playerID, Command command, String[] argList)
    {
        this.playerID = playerID;
        this.command = command;
        this.argList = argList;
    }
}
