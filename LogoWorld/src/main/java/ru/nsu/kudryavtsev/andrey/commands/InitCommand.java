package ru.nsu.kudryavtsev.andrey.commands;

import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды инициализации.
 */
public class InitCommand implements Command
{
    /**
     * Дефолтный конструтор.
     */
    public InitCommand(){}

    /**
     * Функция выполнения команды инициализации.
     * Инициализирует поле и исполнителя.
     * Если координаты исполнителя выходят за границы поля, то усекает координаты так, что они будут принадлежать границам.
     * @param field поле.
     * @param executor исполнитель.
     * @param argList список аргументов.
     * @throws NumberFormatException если невозможно представить один из аргументов в виде числа типа int.
     */
    @Override
    public void execute(Field field, Executor executor, String[] argList) throws NumberFormatException
    {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.log(Level.INFO, "INIT command execution");

        int width = Integer.parseInt(argList[1]);
        int height = Integer.parseInt(argList[2]);
        int curX = Integer.parseInt(argList[3]) % width;
        int curY = Integer.parseInt(argList[4]) % height;

        field.initialize(width, height);
        executor.initialize(curX, curY);
    }
}
