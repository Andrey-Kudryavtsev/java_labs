package ru.nsu.kudryavtsev.andrey.commands;

import ru.nsu.kudryavtsev.andrey.model.GameModel;

public class PlayCommand implements Command
{
    @Override
    public void execute(int playerID, GameModel model, String[] argList)
    {
        model.toPlay();
    }
}
