package ru.nsu.kudryavtsev.andrey.commands;

import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды конца рисования.
 */
public class WardCommand implements Command
{
    /**
     * Дефолтный конструтор.
     */
    public WardCommand() {}

    /**
     * Функция выполнения команды конца рисования.
     * Выводит исполнителя из режима рисования.
     * @param field поле.
     * @param executor исполнитель.
     * @param argList список аргументов.
     */
    @Override
    public void execute(Field field, Executor executor, String[] argList)
    {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.log(Level.INFO, "WARD command execution");

        executor.setDraw(false);
    }
}
