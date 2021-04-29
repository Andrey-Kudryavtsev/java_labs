package ru.nsu.kudryavtsev.andrey.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.nsu.kudryavtsev.andrey.executor.DefaultExecutor;
import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.field.ToroidalField;

public class DrawCommandTest
{
    @Test
    public void test1()
    {
        Field field = new ToroidalField(15, 15);
        Executor executor = new DefaultExecutor(7, 7);
        String[] argList = {"DRAW"};

        Command drawCommand = new DrawCommand();
        drawCommand.execute(field, executor, argList);
        Assertions.assertTrue(executor.isDraw());
    }
}
