package ru.nsu.field;

import ru.nsu.point.Point;
import ru.nsu.tiles.Tile;

public interface Field
{
    int getWidth();
    int getHeight();
    boolean inBounds(int x, int y);
    boolean inBounds(Point p);
    Tile getTile(int x, int y);
    Tile getTile(Point p);
    void setTile(int x, int y, Tile tile);
    void setTile(Point p, Tile tile);
}
