package ru.nsu.point;

public class ZeroPoint implements Point
{
    public static final ZeroPoint INSTANCE = new ZeroPoint();

    private ZeroPoint(){}

    @Override
    public int component(int i) throws IllegalArgumentException
    {
        if (i < 0 || i > 1) throw new IllegalArgumentException();
        return 0;
    }

    @Override
    public int manhattanDistance(Point p)
    {
        if (this == p) return 0;
        return p.component(0) + p.component(1);
    }

    @Override
    public String toString()
    {
        return ("(" + 0 + ", " + 0 + ")");
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return p.component(0) == 0 && p.component(1) == 0;
    }

    @Override
    public int compareTo(Point p) throws NullPointerException
    {
        if (this == p) return 0;
        if (p == null) throw new NullPointerException();
        return 0 != p.component(0) ? Integer.compare(0, p.component(0)) : Integer.compare(0, p.component(1));
    }
}
