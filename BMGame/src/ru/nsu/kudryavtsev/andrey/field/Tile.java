package ru.nsu.kudryavtsev.andrey.field;

import ru.nsu.kudryavtsev.andrey.enums.TileType;

public class Tile
{
    private TileType prevTile;
    private TileType currTile;

    public Tile(TileType tile)
    {
        prevTile = tile;
        currTile = tile;
    }

    public TileType getCurrTile()
    {
        return currTile;
    }

    public TileType getPrevTile()
    {
        return prevTile;
    }

    public void setTile(TileType newTile)
    {
        prevTile = currTile;
        currTile = newTile;
    }

    public void returnTile()
    {
        currTile = prevTile;
        prevTile = TileType.FLOOR;
    }
}
