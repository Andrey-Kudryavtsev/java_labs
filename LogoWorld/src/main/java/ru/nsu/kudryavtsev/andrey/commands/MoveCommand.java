package ru.nsu.kudryavtsev.andrey.commands;

import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.tiles.PaintTile;
import ru.nsu.kudryavtsev.andrey.enums.Directions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды передвижения.
 */
public class MoveCommand implements Command
{
    /**
     * Дефолтный конструтор.
     */
    public MoveCommand() {}

    /**
     * Функция выполнения команды передвижения.
     * Пошагово перемещает исполнителя на новые координаты, определяемые направлением и количеством шагов.
     * Если исполнитель находится в режиме рисования, то закрашивает клетку под ним на каждом шаге.
     * @param field поле.
     * @param executor исполнитель.
     * @param argList список аргументов.
     * @throws IllegalArgumentException если один из аргументов не является корректным.
     */
    @Override
    public void execute(Field field, Executor executor, String[] argList) throws IllegalArgumentException
    {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.log(Level.INFO, "MOVE command execution");

        Directions dir = Directions.valueOf(argList[1]);
        int steps = Integer.parseInt(argList[2]);
        int curX = executor.getCoords().component(0);
        int curY = executor.getCoords().component(1);
        int dx = 0;
        int dy = 0;
        int width = field.getWidth();
        int height = field.getHeight();

        for (int i = 0; i < steps; ++i)
        {
            switch (dir)
            {
                case U -> {
                    dy = (curY + height - 1) % height - curY;
                    curY += dy;
                }
                case R -> {
                    dx = (curX + width + 1) % width - curX;
                    curX += dx;
                }
                case D -> {
                    dy = (curY + height + 1) % height - curY;
                    curY += dy;
                }
                case L -> {
                    dx = (curX + width - 1) % width - curX;
                    curX += dx;
                }
            }
            executor.move(dx, dy);
            if (executor.isDraw())
            {
                field.setTile(curX, curY, PaintTile.INSTANCE);
            }
        }
    }
}
