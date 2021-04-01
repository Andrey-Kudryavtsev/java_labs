package ru.nsu.view;

import ru.nsu.executor.Executor;
import ru.nsu.field.Field;

public interface View
{
    void draw(Field field, Executor executor);
}
