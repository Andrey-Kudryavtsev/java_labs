package ru.nsu.factory;

import ru.nsu.CommandTypes;
import ru.nsu.commands.Command;

public interface CommandFactory
{
    Command createCommand(CommandTypes type);
}
