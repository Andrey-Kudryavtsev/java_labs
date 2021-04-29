package ru.nsu.kudryavtsev.andrey.view;

import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.field.Field;

/**
 * Класс отрисовки поля и исполнителя в консоли.
 */
public class ConsoleView implements View
{
    /**
     * Функция отрисовки поля и исполнителя в консоли.
     * @param field поле.
     * @param executor исполнитель.
     */
    @Override
    public void draw(Field field, Executor executor)
    {
        int execX = executor.getCoords().component(0);
        int execY = executor.getCoords().component(1);
        for (int i = 0; i < field.getHeight(); ++i)
        {
            System.out.print("| ");
            for (int j = 0; j < field.getWidth(); ++j)
            {
                if (execX == j && execY == i) System.out.print("@ ");
                else System.out.print(field.getTile(j, i) + " ");
            }
            System.out.println("|");
        }
    }
}
