package ru.nsu.kudryavtsev.andrey.commands;

import ru.nsu.kudryavtsev.andrey.enums.Direction;
import ru.nsu.kudryavtsev.andrey.model.GameModel;

public class MoveCommand implements Command
{
    @Override
    public void execute(int playerID, GameModel model, String[] argList)
    {
        if (model == null || argList == null)
            throw new NullPointerException("Пустой аргумент");
        Direction dir = Direction.valueOf(argList[1]); // throws illegalArgumentException
        int dx = 0;
        int dy = 0;
        switch (dir)
        {
            case U -> {
                dy = -1;
            }
            case R -> {
                dx = 1;
            }
            case D -> {
                dy = 1;
            }
            case L -> {
                dx = -1;
            }
            default -> {
                throw new IllegalArgumentException("Неизвестное направление");
            }
        }
        model.movePlayer(playerID, dx, dy);
    }
}
