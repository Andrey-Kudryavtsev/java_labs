package ru.nsu.kudryavtsev.andrey.commands;

import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.tiles.PaintTile;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды начала рисования.
 */
public class DrawCommand implements Command
{
    /**
     * Дефолтный конструтор.
     */
    public DrawCommand(){}

    /**
     * Функция выполнения команды начала рисования.
     * Переводит исполнителя в режим рисования.
     * Закрашивает клетку под исполнителем.
     * @param field поле.
     * @param executor исполнитель.
     * @param argList список аргументов.
     */
    @Override
    public void execute(Field field, Executor executor, String[] argList)
    {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.log(Level.INFO, "DRAW command execution");

        int curX = executor.getCoords().component(0);
        int curY = executor.getCoords().component(1);
        executor.setDraw(true);
        field.setTile(curX, curY, PaintTile.INSTANCE);
    }
}
