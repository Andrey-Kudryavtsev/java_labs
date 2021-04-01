package ru.nsu.view;

import ru.nsu.executor.Executor;
import ru.nsu.field.Field;
import ru.nsu.point.DefaultPoint;
import ru.nsu.point.Point;

public class ConsoleView implements View
{
    @Override
    public void draw(Field field, Executor executor)
    {
        int execX = executor.getCoords().component(0);
        int execY = executor.getCoords().component(1);
        for (int i = 0; i < field.getWidth(); ++i)
        {
            System.out.print("| ");
            for (int j = 0; j < field.getHeight(); ++j)
            {
                if (execX == i && execY == j) System.out.print("@ ");
                else System.out.print(field.getTile(i, j) + " ");
            }
            System.out.println("|");
        }
    }
}
