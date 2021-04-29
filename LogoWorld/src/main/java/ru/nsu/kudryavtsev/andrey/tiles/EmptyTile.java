package ru.nsu.kudryavtsev.andrey.tiles;

/**
 * Singleton-класс пустой клетки.
 */
public class EmptyTile extends AbstractTile
{
    /** Единственный экземпляр данного класса. */
    public static final EmptyTile INSTANCE = new EmptyTile();

    /**
     * Конструктор - создание нового объекта.
     * Приватный, необходим только для инициализации поля INSTANCE.
     * Устанавливает символьное представление - '.'.
     */
    private EmptyTile()
    {
        super.form = '.';
    }
}
