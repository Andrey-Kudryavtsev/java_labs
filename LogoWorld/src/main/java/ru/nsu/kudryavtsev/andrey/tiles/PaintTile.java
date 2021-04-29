package ru.nsu.kudryavtsev.andrey.tiles;

/**
 * Singleton-класс закрашенной клетки.
 */
public class PaintTile extends AbstractTile
{
    /** Единственный экземпляр данного класса. */
    public static final PaintTile INSTANCE = new PaintTile();

    /**
     * Конструктор - создание нового объекта.
     * Приватный, необходим только для инициализации поля INSTANCE.
     * Устанавливает символьное представление - '#'.
     */
    private PaintTile()
    {
        super.form = '#';
    }
}
