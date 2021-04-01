package ru.nsu.input_handler;

import ru.nsu.CommandTypes;
import ru.nsu.commands.Command;

public interface InputHandler
{
    CommandTypes handle(String input);
}
