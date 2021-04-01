package ru.nsu.input_handler;

import ru.nsu.CommandTypes;

public class DefaultInputHandler implements InputHandler
{
    @Override
    public CommandTypes handle(String input)
    {
        CommandTypes type = null;
        if (input.matches("INIT [0-9]+ [0-9]+ [0-9]+ [0-9]+"))
        {
            type = CommandTypes.INIT;
        } else if (input.matches("MOVE [LRUD] [0-9]+"))
        {
            type = CommandTypes.MOVE;
        } else if (input.matches("DRAW"))
        {
            type = CommandTypes.DRAW;
        } else if (input.matches("WARD"))
        {
            type = CommandTypes.WARD;
        } else if (input.matches("TELEPORT [0-9]+ [0-9]+"))
        {
            type = CommandTypes.TELEPORT;
        }
        return type;
    }
}
