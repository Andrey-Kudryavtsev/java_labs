package ru.nsu.kudryavtsev.andrey.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.kudryavtsev.andrey.commands.Command;

import java.io.IOException;

public class FactoryTest
{
    @Test
    public void wrongFileTest()
    {
        Assertions.assertThrows(IOException.class, () -> new DefaultCommandFactory("wrongfile"));
    }

    @Test
    public void createCommandTest() throws IOException
    {
        CommandFactory factory = new DefaultCommandFactory("config");
        Command command;

        command = factory.createCommand("INIT");
        Assertions.assertNotEquals(null, command);

        command = factory.createCommand("MOVE");
        Assertions.assertNotEquals(null, command);

        command = factory.createCommand("TELEPORT");
        Assertions.assertNotEquals(null, command);

        command = factory.createCommand("DRAW");
        Assertions.assertNotEquals(null, command);

        command = factory.createCommand("WARD");
        Assertions.assertNotEquals(null, command);
    }
}
