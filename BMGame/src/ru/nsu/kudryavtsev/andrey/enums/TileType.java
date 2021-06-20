package ru.nsu.kudryavtsev.andrey.enums;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum TileType
{
    FLOOR(new Color(250, 150, 20), '.', true, false, false),
    WALL(Color.GRAY, '#', false, false, true),
    BOMB(Color.RED, '*', false, false, false),
    PLAYER(Color.GREEN, '@', false, false, true),
    DEAD_PLAYER(Color.BLUE, 'F', true, false, false),
    EXPLOSION(Color.ORANGE, 'X', true, true, false);

//    private final Image image;
    private final Color color;
    private final char form;
    private final boolean isPassable;
    private final boolean isDangerous;
    private final boolean isExplosible;

    private TileType(Color color, char form, boolean isPassable, boolean isDangerous, boolean isExplosible)
    {
        this.color = color;
        this.form = form;
        this.isPassable = isPassable;
        this.isDangerous = isDangerous;
        this.isExplosible = isExplosible;
    }

    public Color getColor()
    {
        return color;
    }

    public char getForm()
    {
        return form;
    }

    public static TileType getTileType(char form)
    {
        for (TileType tileType : values())
        {
            if (tileType.form == form)
                return tileType;
        }
        throw new IllegalArgumentException("Клетка не существует");
    }

    public boolean isPassable()
    {
        return isPassable;
    }

    public boolean isDangerous()
    {
        return isDangerous;
    }

    public boolean isExplosible()
    {
        return isExplosible;
    }
}
