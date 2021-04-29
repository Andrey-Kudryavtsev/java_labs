package ru.nsu.kudryavtsev.andrey.field;

import ru.nsu.kudryavtsev.andrey.point.Point;
import ru.nsu.kudryavtsev.andrey.tiles.Tile;

/**
 * Интерфейс поля.
 */
public interface Field
{
    /**
     * Функция инициализации поля.
     * @param width ширина поля.
     * @param height высота поля.
     */
    void initialize(int width, int height);

    /**
     * Функция, возвращающая ширину поля.
     * @return Возвращает ширину поля.
     */
    int getWidth();

    /**
     * Функция, возвращающая высоту поля.
     * @return Возвращает высоту поля.
     */
    int getHeight();

    /**
     * Функция, проверяющая, находится ли точка в границах поля.
     * @param x координата x.
     * @param y координата y.
     * @return Возвращает true, если лежит в границах; false, если нет.
     */
    boolean inBounds(int x, int y);

    /**
     * Функция, проверяющая, находится ли точка в границах поля.
     * @param p проверяемая точка.
     * @return Возвращает true, если лежит в границах; false, если нет.
     */
    boolean inBounds(Point p);

    /**
     * Функция, возвращающая клетку по координатам.
     * @param x координата x.
     * @param y координата y.
     * @return Возвращает клетку на переданных координатах.
     */
    Tile getTile(int x, int y);

    /**
     * Функция, возвращающая клетку по координатам.
     * @param p точка, в которой находится нужная клетка.
     * @return Возвращает клетку на переданных координатах.
     */
    Tile getTile(Point p);

    /**
     * Функция, размещающая клетку по координатам.
     * @param x - координата x.
     * @param y - координата y.
     * @param tile клетка, которую необходимо разместить.
     */
    void setTile(int x, int y, Tile tile);

    /**
     * Функция, размещающая клетку по координатам.
     * @param p точка, в которую нужно поместить клетку.
     * @param tile клетка, которую необходимо разместить.
     */
    void setTile(Point p, Tile tile);
}
