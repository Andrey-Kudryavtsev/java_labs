package ru.nsu.field;

import ru.nsu.point.Point;
import ru.nsu.tiles.EmptyTile;
import ru.nsu.tiles.Tile;

public class ToroidalField implements Field
{
    private final int width;
    private final int height;
    private Tile[][] field;

    public ToroidalField(int width, int height)
    {
        this.width = width;
        this.height = height;
        field = new Tile[width][height];

        for (int i = 0; i < width; ++i)
        {
            for (int j = 0; j < height; ++j)
            {
                field[i][j] = EmptyTile.INSTANCE;
            }
        }
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    @Override
    public boolean inBounds(int x, int y)
    {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    @Override
    public boolean inBounds(Point p)
    {
        int x = p.component(0);
        int y = p.component(1);
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    @Override
    public Tile getTile(int x, int y)
    {
        if (!inBounds(x, y)) throw new IndexOutOfBoundsException();
        return field[x][y];
    }

    @Override
    public Tile getTile(Point p) throws IndexOutOfBoundsException
    {
        if (!inBounds(p)) throw new IndexOutOfBoundsException();
        return field[p.component(0)][p.component(1)];
    }

    @Override
    public void setTile(int x, int y, Tile tile)
    {
        if (!inBounds(x, y)) throw new IndexOutOfBoundsException();
        field[x][y] = tile;
    }

    @Override
    public void setTile(Point p, Tile tile)
    {
        if (!inBounds(p)) throw new IndexOutOfBoundsException();
        field[p.component(0)][p.component(1)] = tile;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder(width * height);
        for (int i = 0; i < width; ++i)
        {
            str.append("| ");
            for (int j = 0; j < height; ++j)
            {
                String tmp = field[i][j].getForm() + " ";
                str.append(tmp);
            }
            str.append("|\n");
        }
        return str.toString();
    }
}
