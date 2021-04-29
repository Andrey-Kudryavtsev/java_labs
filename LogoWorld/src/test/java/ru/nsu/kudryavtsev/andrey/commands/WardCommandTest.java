package ru.nsu.kudryavtsev.andrey.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.kudryavtsev.andrey.executor.DefaultExecutor;
import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.field.ToroidalField;

public class WardCommandTest
{
    @Test
    public void test1()
    {
        Field field = new ToroidalField(10, 10);
        Executor executor = new DefaultExecutor(5, 5);
        String[] argList = {"WARD"};

        Command wardCommand = new WardCommand();
        wardCommand.execute(field, executor, argList);
        Assertions.assertFalse(executor.isDraw());
    }
}
