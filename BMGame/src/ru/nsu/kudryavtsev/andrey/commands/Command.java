package ru.nsu.kudryavtsev.andrey.commands;

import ru.nsu.kudryavtsev.andrey.model.GameModel;

public interface Command
{
    void execute(int playerID, GameModel model, String[] argList);
}
