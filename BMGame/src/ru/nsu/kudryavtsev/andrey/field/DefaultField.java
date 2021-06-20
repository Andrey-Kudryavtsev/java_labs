package ru.nsu.kudryavtsev.andrey.field;

import ru.nsu.kudryavtsev.andrey.enums.TileType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class DefaultField implements Field
{
    private final int width;
    private final int height;
    private final Tile[][] field;

//    public DefaultField()
//    {
//        this.width = 15;
//        this.height = 15;
//
//        field = new TileType[][] {
//                {TileType.PLAYER, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR},
//                {TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR},
//                {TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR},
//                {TileType.WALL, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR},
//                {TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR},
//                {TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR},
//                {TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR},
//                {TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR},
//                {TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR},
//                {TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL},
//                {TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.FLOOR, TileType.FLOOR},
//                {TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.WALL},
//                {TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.WALL, TileType.WALL},
//                {TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.FLOOR},
//                {TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.FLOOR, TileType.WALL, TileType.FLOOR, TileType.WALL, TileType.WALL, TileType.FLOOR, TileType.PLAYER},
//        };
//    }

    public DefaultField(String fileName) throws IOException
    {
        Scanner scanner = new Scanner(new FileInputStream("map1.txt"));
        width = scanner.nextInt();
        height = scanner.nextInt();
        scanner.nextLine();
        field = new Tile[height][width];
        for (int y = 0; y < height; ++y)
        {
            String line = scanner.nextLine();
            for (int x = 0; x < width; ++x)
            {
                char form = line.charAt(x);
                field[y][x] = new Tile(TileType.getTileType(form));
            }
        }
    }

//    public DefaultField(int width, int height)
//    {
//        if (width < 0 || height < 0)
//            throw new IllegalArgumentException("Ширина и высота должны быть положительными");
//        this.width = width;
//        this.height = height;
//
//        field = new TileType[height][width];
//
//        for (int y = 0; y < height; ++y)
//        {
//            for (int x = 0; x < width; ++x)
//            {
//                field[y][x] = TileType.FLOOR;
//            }
//        }
//    }

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
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    @Override
    public TileType getCurrTile(int x, int y)
    {
        if (!inBounds(x, y)) throw new IndexOutOfBoundsException("Точка выходит за границы поля");
        return field[y][x].getCurrTile();
    }

    @Override
    public TileType getPrevTile(int x, int y)
    {
        if (!inBounds(x, y)) throw new IndexOutOfBoundsException("Точка выходит за границы поля");
        return field[y][x].getPrevTile();
    }

    @Override
    public void setTile(int x, int y, TileType tileType)
    {
        if (!inBounds(x, y))
            throw new IndexOutOfBoundsException("Точка выходит за границы поля");
        if (tileType == null)
            throw new NullPointerException("Пустой аргумент");
        field[y][x].setTile(tileType);
    }

    @Override
    public void returnTile(int x, int y)
    {
        field[y][x].returnTile();
    }
}
