package ru.nsu.kudryavtsev.andrey.commands;

import ru.nsu.kudryavtsev.andrey.model.GameModel;

public class ExplodeCommand implements Command
{
    @Override
    public void execute(int playerID, GameModel model, String[] argList)
    {
        int x = Integer.parseInt(argList[1]);
        int y = Integer.parseInt(argList[2]);

//        model.explodeBomb(playerID, x, y);
    }
}
