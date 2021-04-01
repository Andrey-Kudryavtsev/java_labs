package ru.nsu.executor;

import ru.nsu.point.DefaultPoint;
import ru.nsu.point.Point;

public class DefaultExecutor implements Executor
{
    private Point coords;
    private boolean isDraw = false;

    public DefaultExecutor(int x, int y)
    {
        this.coords = new DefaultPoint(x, y); // TODO: лучше просто Point
    }
    public DefaultExecutor(Point coords)
    {
        this.coords = coords;
    }

    @Override
    public Point getCoords()
    {
        return coords;
    }

    @Override
    public boolean isDraw()
    {
        return isDraw;
    }

    @Override
    public void switchDraw()
    {
        isDraw = !isDraw;
    }
}
