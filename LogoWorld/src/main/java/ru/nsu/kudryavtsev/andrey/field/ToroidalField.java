package ru.nsu.kudryavtsev.andrey.field;

import ru.nsu.kudryavtsev.andrey.point.Point;
import ru.nsu.kudryavtsev.andrey.tiles.EmptyTile;
import ru.nsu.kudryavtsev.andrey.tiles.Tile;


/**
 * Класс тороидального поля.
 */
public class ToroidalField implements Field
{
    /** ширина. */
    private int width;
    /** высота. */
    private int height;
    /** поле. */
    private Tile[][] field;

    /**
     * Функция инициализации поля.
     * @param width ширина поля.
     * @param height высота поля.
     */
    @Override
    public void initialize(int width, int height)
    {
        if (width < 0 || height < 0)
        {
            width = 0;
            height = 0;
        }

        this.width = width;
        this.height = height;
        field = new Tile[height][width];

        for (int y = 0; y < height; ++y)
        {
            for (int x = 0; x < width; ++x)
            {
                field[y][x] = EmptyTile.INSTANCE;
            }
        }
    }

    /**
     * Конструктор - создание нового объекта.
     * Использует функцию initialize.
     * @param width ширина поля.
     * @param height высота поля.
     */
    public ToroidalField(int width, int height)
    {
        initialize(width, height);
    }

    /**
     * Функция, возвращающая ширину поля.
     * @return Возвращает ширину поля.
     */
    @Override
    public int getWidth()
    {
        return width;
    }

    /**
     * Функция, возвращающая высоту поля.
     * @return Возвращает высоту поля.
     */
    @Override
    public int getHeight()
    {
        return height;
    }

    /**
     * Функция, проверяющая, находится ли точка в границах поля.
     * @param x координата x.
     * @param y координата y.
     * @return Возвращает true, если лежит в границах; false, если нет.
     */
    @Override
    public boolean inBounds(int x, int y)
    {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Функция, проверяющая, находится ли точка в границах поля.
     * @param p проверяемая точка.
     * @return Возвращает true, если лежит в границах; false, если нет.
     */
    @Override
    public boolean inBounds(Point p)
    {
        int x = p.component(0);
        int y = p.component(1);
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Функция, возвращающая клетку по координатам.
     * @param x координата x.
     * @param y координата y.
     * @return Возвращает клетку на переданных координатах/
     */
    @Override
    public Tile getTile(int x, int y)
    {
        if (!inBounds(x, y)) throw new IndexOutOfBoundsException();
        return field[y][x];
    }

    /**
     * Функция, возвращающая клетку по координатам.
     * @param p точка, в которой находится нужная клетка.
     * @return Возвращает клетку на переданных координатах.
     */
    @Override
    public Tile getTile(Point p) throws IndexOutOfBoundsException
    {
        int x = p.component(0);
        int y = p.component(1);
        if (!inBounds(p)) throw new IndexOutOfBoundsException();
        return field[y][x];
    }

    /**
     * Функция, размещающая клетку по координатам.
     * @param x - координата x.
     * @param y - координата y.
     * @param tile клетка, которую необходимо разместить.
     */
    @Override
    public void setTile(int x, int y, Tile tile)
    {
        if (!inBounds(x, y)) throw new IndexOutOfBoundsException();
        field[y][x] = tile;
    }

    /**
     * Функция, размещающая клетку по координатам.
     * @param p точка, в которую нужно поместить клетку.
     * @param tile клетка, которую необходимо разместить.
     */
    @Override
    public void setTile(Point p, Tile tile)
    {
        int x = p.component(0);
        int y = p.component(1);
        if (!inBounds(p)) throw new IndexOutOfBoundsException();
        field[y][x] = tile;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder(width * height);
        for (int y = 0; y < height; ++y)
        {
            str.append("| ");
            for (int x = 0; x < width; ++x)
            {
                String tmp = field[y][x].getForm() + " ";
                str.append(tmp);
            }
            str.append("|\n");
        }
        return str.toString();
    }
}
