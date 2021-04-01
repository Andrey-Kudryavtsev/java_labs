package ru.nsu.executor;

import ru.nsu.point.Point;

public interface Executor
{
    Point getCoords();
    // TODO: move();
    boolean isDraw();
    void switchDraw();
}
