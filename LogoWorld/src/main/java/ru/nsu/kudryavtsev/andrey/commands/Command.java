package ru.nsu.kudryavtsev.andrey.commands;

import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;

/**
 * Интерфейс команды.
 */
public interface Command
{
    /**
     * Функция выполнения команды.
     * @param field поле.
     * @param executor исполнитель.
     * @param argList список аргументов.
     */
    void execute(Field field, Executor executor, String[] argList);
}
