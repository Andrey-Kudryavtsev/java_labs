package ru.nsu.kudryavtsev.andrey.view;

import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;

/**
 * Интерфейс отрисовки поля и исполнителя.
 */
public interface View
{
    /**
     * Функция отрисовки поля и исполнителя.
     * @param field поле.
     * @param executor исполнитель.
     */
    void draw(Field field, Executor executor);
}
