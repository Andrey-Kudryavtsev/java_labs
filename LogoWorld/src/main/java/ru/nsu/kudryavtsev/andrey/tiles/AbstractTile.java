package ru.nsu.kudryavtsev.andrey.tiles;

/**
 * Абстрактный класс клетки поля с полем form.
 * Необходим для того, чтобы сократить объем кода для конкретных клеток.
 */
public abstract class AbstractTile implements Tile
{
    /** Символьное представление. */
    protected char form;

    /**
     * Функция получения символа, ассоциированного с клеткой.
     * @return Возвращает символьное представление клетки.
     */
    @Override
    public char getForm()
    {
        return form;
    }

    /**
     * Функция, переводящая символьное представление клетки в строковое.
     * @return Возврщает строковое представление клетки.
     */
    @Override
    public String toString()
    {
        return Character.toString(form);
    }
}
