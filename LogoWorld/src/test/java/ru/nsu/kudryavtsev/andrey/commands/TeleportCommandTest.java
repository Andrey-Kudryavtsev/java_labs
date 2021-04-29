package ru.nsu.kudryavtsev.andrey.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.kudryavtsev.andrey.executor.DefaultExecutor;
import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.field.ToroidalField;

public class TeleportCommandTest
{
    @Test
    public void basicTest()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(6, 5);
        String[] argList = {"TELEPORT", "10", "7"};

        Command tpCommand = new TeleportCommand();
        tpCommand.execute(field, executor, argList);

        Assertions.assertEquals(10, executor.getCoords().component(0));
        Assertions.assertEquals(7, executor.getCoords().component(1));
    }

    @Test
    public void CornerTest1()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(6, 5);
        String[] argList = {"TELEPORT", "0", "0"};

        Command tpCommand = new TeleportCommand();
        tpCommand.execute(field, executor, argList);

        Assertions.assertEquals(0, executor.getCoords().component(0));
        Assertions.assertEquals(0, executor.getCoords().component(1));
    }

    @Test
    public void CornerTest2()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(6, 5);
        String[] argList = {"TELEPORT", "14", "0"};

        Command tpCommand = new TeleportCommand();
        tpCommand.execute(field, executor, argList);

        Assertions.assertEquals(14, executor.getCoords().component(0));
        Assertions.assertEquals(0, executor.getCoords().component(1));
    }

    @Test
    public void CornerTest3()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(6, 5);
        String[] argList = {"TELEPORT", "0", "9"};

        Command tpCommand = new TeleportCommand();
        tpCommand.execute(field, executor, argList);

        Assertions.assertEquals(0, executor.getCoords().component(0));
        Assertions.assertEquals(9, executor.getCoords().component(1));
    }

    @Test
    public void CornerTest4()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(6, 5);
        String[] argList = {"TELEPORT", "14", "9"};

        Command tpCommand = new TeleportCommand();
        tpCommand.execute(field, executor, argList);

        Assertions.assertEquals(14, executor.getCoords().component(0));
        Assertions.assertEquals(9, executor.getCoords().component(1));
    }

    @Test
    public void outOfBoundsTest()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(6, 5);
        String[] argList = {"TELEPORT", "15", "10"};

        Command tpCommand = new TeleportCommand();
        tpCommand.execute(field, executor, argList);

        Assertions.assertEquals(6, executor.getCoords().component(0));
        Assertions.assertEquals(5, executor.getCoords().component(1));
    }

    @Test
    public void bigIntTest()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(6, 5);
        String[] argList = {"TELEPORT", "2532446536236462363634634623", "356354765788978697586745364253436"};

        Command tpCommand = new TeleportCommand();

        Assertions.assertThrows(NumberFormatException.class, () -> tpCommand.execute(field, executor, argList));
    }
}
