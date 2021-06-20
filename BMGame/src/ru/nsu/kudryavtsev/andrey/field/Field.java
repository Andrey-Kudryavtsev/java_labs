package ru.nsu.kudryavtsev.andrey.field;

import ru.nsu.kudryavtsev.andrey.enums.TileType;

public interface Field
{
    int getWidth();
    int getHeight();
    boolean inBounds(int x, int y);
    TileType getCurrTile(int x, int y);
    TileType getPrevTile(int x, int y);
    void setTile(int x, int y, TileType tileType);
    void returnTile(int x, int y);
}
