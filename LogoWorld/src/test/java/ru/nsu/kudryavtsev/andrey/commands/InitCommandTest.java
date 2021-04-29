package ru.nsu.kudryavtsev.andrey.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.kudryavtsev.andrey.executor.DefaultExecutor;
import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.field.ToroidalField;

public class InitCommandTest
{
    @Test
    public void rightInitializationTest1()
    {
        Field field = new ToroidalField(0, 0);
        Executor executor = new DefaultExecutor(0, 0);
        String[] argList = {"INIT", "10", "15", "5", "7"};

        Command initCommand = new InitCommand();
        initCommand.execute(field, executor, argList);

        Assertions.assertEquals(10, field.getWidth());
        Assertions.assertEquals(15, field.getHeight());
        Assertions.assertEquals(5, executor.getCoords().component(0));
        Assertions.assertEquals(7, executor.getCoords().component(1));
    }

    @Test
    public void rightInitializationTest2()
    {
        Field field = new ToroidalField(0, 0);
        Executor executor = new DefaultExecutor(0, 0);
        String[] argList = {"INIT", "13", "17", "68", "94"};

        Command initCommand = new InitCommand();
        initCommand.execute(field, executor, argList);

        Assertions.assertEquals(13, field.getWidth());
        Assertions.assertEquals(17, field.getHeight());
        Assertions.assertEquals(68 % 13, executor.getCoords().component(0));
        Assertions.assertEquals(94 % 17, executor.getCoords().component(1));
    }

    @Test
    public void bigIntTest()
    {
        Field field = new ToroidalField(15, 10);
        Executor executor = new DefaultExecutor(6, 5);
        String[] argList = {"INIT",
                            "2532446536236462363634634623",
                            "356354765788978697586745364253436",
                            "3546474856756543754869576556",
                            "6354754535468576745634365746"};

        Command initCommand = new InitCommand();

        Assertions.assertThrows(NumberFormatException.class, () -> initCommand.execute(field, executor, argList));
    }
}
