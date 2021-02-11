package morse_code;

import morse_code.command.*;

public class InputHandler
{
    public ICommand handleInput(String input) throws MyException
    {
        ICommand command;
        if (input.matches("encode [\\w./]+"))
        {
            command = new EncodeCommand(input.substring(7));
        } else if (input.matches("decode [\\w./]+"))
        {
            command = new DecodeCommand(input.substring(7));
        } else
        {
            throw new MyException("wrong command");
        }
        return command;
    }
}
