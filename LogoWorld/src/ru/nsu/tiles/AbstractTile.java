package ru.nsu.tiles;

public class AbstractTile implements Tile
{
    protected char form;

    @Override
    public char getForm()
    {
        return form;
    }

    @Override
    public String toString()
    {
        return Character.toString(form);
    }
}
