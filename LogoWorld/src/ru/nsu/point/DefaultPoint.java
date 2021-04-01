package ru.nsu.point;

public class DefaultPoint implements Point
{
    private int x;
    private int y;

    public DefaultPoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public DefaultPoint(DefaultPoint other)
    {
        this.x = other.x;
        this.y = other.y;
    }

    @Override
    public int component(int i) throws IllegalArgumentException
    {
        return switch (i) {
            case 0 -> x;
            case 1 -> y;
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public int manhattanDistance(Point p)
    {
        if (p == this) return 0;
        if (p == ZeroPoint.INSTANCE) return x+y;
        return Math.abs(x - p.component(0)) + Math.abs(y - p.component(1));
    }

    @Override
    public String toString()
    {
        return ("(" + x + ", " + y + ")");
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return p.component(0) == x && p.component(1) == y;
    }

    @Override
    public int compareTo(Point p) throws NullPointerException,ClassCastException
    {
        if (p == this) return 0;
        if (p == null) throw new NullPointerException();
        return x != p.component(0) ? Integer.compare(x, p.component(0)) : Integer.compare(y, p.component(1));
    }
}
