package ru.nsu.point;

public interface Point extends Comparable<Point>
{
    int component(int i);
    int manhattanDistance(Point p);
    boolean equals(Object o);
    int compareTo(Point p);
}
