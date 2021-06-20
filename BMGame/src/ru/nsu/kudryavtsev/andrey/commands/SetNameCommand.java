package ru.nsu.kudryavtsev.andrey.commands;

import ru.nsu.kudryavtsev.andrey.model.GameModel;

public class SetNameCommand implements Command
{
    @Override
    public void execute(int playerID, GameModel model, String[] argList)
    {
        model.setUsername(argList[1]);
    }
}
