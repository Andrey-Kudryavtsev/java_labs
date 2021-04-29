package ru.nsu.kudryavtsev.andrey.commands;

import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.tiles.PaintTile;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды телепортации.
 */
public class TeleportCommand implements Command
{
    /**
     * Дефолтный конструтор.
     */
    public TeleportCommand(){}

    /**
     * Функция выполнения команды телепортации.
     * Перемещает исполнителя на новые координаты.
     * Если новые координаты находятся за пределами поля, то телепортация не происходит, а пользователю выводится соответствующее сообщение.
     * Если исполнитель находится в режиме рисования, то после перемещения клетка под ним закрашивается.
     * @param field поле.
     * @param executor исполнитель.
     * @param argList список аргументов.
     * @throws NumberFormatException если невозможно представить один из аргументов в виде числа типа int.
     */
    @Override
    public void execute(Field field, Executor executor, String[] argList) throws NumberFormatException
    {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.log(Level.INFO, "TELEPORT command execution");

        int newX = Integer.parseInt(argList[1]);
        int newY = Integer.parseInt(argList[2]);
        int curX = executor.getCoords().component(0);
        int curY = executor.getCoords().component(1);
        int dx = newX - curX;
        int dy = newY - curY;

        if (!field.inBounds(newX, newY))
        {
            logger.log(Level.WARNING, "Wrong coordinates for TELEPORT");
            System.out.println("Wrong coordinates for TELEPORT");
            return;
        }
        executor.move(dx, dy);
        if (executor.isDraw())
        {
            field.setTile(newX, newY, PaintTile.INSTANCE);
        }
    }
}
