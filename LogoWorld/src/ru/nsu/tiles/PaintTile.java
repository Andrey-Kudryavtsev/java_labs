package ru.nsu.tiles;

public class PaintTile extends AbstractTile
{
    public static final PaintTile INSTANCE = new PaintTile();

    private PaintTile()
    {
        super.form = '#';
        System.out.println("PaintTile constructor is working");
    }
}
