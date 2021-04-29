package ru.nsu.kudryavtsev.andrey.factory;

import ru.nsu.kudryavtsev.andrey.commands.Command;

/**
 * Интерфейс фабрики команд.
 */
public interface CommandFactory
{
    /**
     * Функция создания команды.
     * @param commandName имя команды.
     * @return Возвращает команду, ассоциированную с commandName.
     */
    Command createCommand(String commandName);
}
