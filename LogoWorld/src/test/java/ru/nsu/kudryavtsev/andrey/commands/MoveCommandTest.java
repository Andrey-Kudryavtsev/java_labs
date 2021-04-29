package ru.nsu.kudryavtsev.andrey.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.kudryavtsev.andrey.executor.DefaultExecutor;
import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.field.ToroidalField;

public class MoveCommandTest
{
    @Test
    public void moveUpTestSmallShift()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(7, 5);
        String[] argList = {"MOVE", "U", "3"};

        Command mvCommand = new MoveCommand();
        mvCommand.execute(field, executor, argList);

        Assertions.assertEquals(7, executor.getCoords().component(0));
        Assertions.assertEquals(2, executor.getCoords().component(1));
    }

    @Test
    public void moveUpTestBigShift()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(7, 5);
        String[] argList = {"MOVE", "U", "68"};

        Command mvCommand = new MoveCommand();
        mvCommand.execute(field, executor, argList);

        Assertions.assertEquals(7, executor.getCoords().component(0));
        Assertions.assertEquals(7, executor.getCoords().component(1));
    }

    @Test
    public void moveLeftTestSmallShift()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(7, 5);
        String[] argList = {"MOVE", "L", "3"};

        Command mvCommand = new MoveCommand();
        mvCommand.execute(field, executor, argList);

        Assertions.assertEquals(4, executor.getCoords().component(0));
        Assertions.assertEquals(5, executor.getCoords().component(1));
    }

    @Test
    public void moveLeftTestBigShift()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(7, 5);
        String[] argList = {"MOVE", "L", "49"};

        Command mvCommand = new MoveCommand();
        mvCommand.execute(field, executor, argList);

        Assertions.assertEquals(3, executor.getCoords().component(0));
        Assertions.assertEquals(5, executor.getCoords().component(1));
    }

    @Test
    public void moveDownTestSmallShift()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(7, 5);
        String[] argList = {"MOVE", "D", "3"};

        Command mvCommand = new MoveCommand();
        mvCommand.execute(field, executor, argList);

        Assertions.assertEquals(7, executor.getCoords().component(0));
        Assertions.assertEquals(8, executor.getCoords().component(1));
    }

    @Test
    public void moveDownTestBigShift()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(7, 5);
        String[] argList = {"MOVE", "D", "37"};

        Command mvCommand = new MoveCommand();
        mvCommand.execute(field, executor, argList);

        Assertions.assertEquals(7, executor.getCoords().component(0));
        Assertions.assertEquals(2, executor.getCoords().component(1));
    }

    @Test
    public void moveRightTestSmallShift()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(7, 5);
        String[] argList = {"MOVE", "R", "3"};

        Command mvCommand = new MoveCommand();
        mvCommand.execute(field, executor, argList);

        Assertions.assertEquals(10, executor.getCoords().component(0));
        Assertions.assertEquals(5, executor.getCoords().component(1));
    }

    @Test
    public void moveRightTestBigShift()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(7, 5);
        String[] argList = {"MOVE", "R", "74"};

        Command mvCommand = new MoveCommand();
        mvCommand.execute(field, executor, argList);

        Assertions.assertEquals(6, executor.getCoords().component(0));
        Assertions.assertEquals(5, executor.getCoords().component(1));
    }

    @Test
    public void BigIntTest()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(7, 5);
        String[] argList = {"MOVE", "U", "12345647432234255643"};

        Command mvCommand = new MoveCommand();

        Assertions.assertThrows(NumberFormatException.class, () -> mvCommand.execute(field, executor, argList));
    }
}
