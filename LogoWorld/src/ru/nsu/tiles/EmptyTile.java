package ru.nsu.tiles;

public class EmptyTile extends AbstractTile
{
    public static final EmptyTile INSTANCE = new EmptyTile();

    private EmptyTile()
    {
        super.form = '.';
        System.out.println("EmptyTile constructor is working");
    }
}
