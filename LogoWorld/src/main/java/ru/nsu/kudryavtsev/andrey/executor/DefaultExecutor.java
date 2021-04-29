package ru.nsu.kudryavtsev.andrey.executor;

import ru.nsu.kudryavtsev.andrey.point.Point;

/**
 * Класс базового исполнителя.
 */
public class DefaultExecutor implements Executor
{
    /** координаты исполнителя. */
    private Point coords;
    /** флаг, отражающий состояние режима рисования. */
    private boolean isDraw = false;

    /**
     * Функция инициализации исполнителя.
     * @param x координата x.
     * @param y координата y.
     */
    @Override
    public void initialize(int x, int y)
    {
        this.coords = new Point(x, y);
    }

    /**
     * Конструктор - создание нового объекта.
     * Использует функцию initialize.
     * @param x координата x.
     * @param y координата y.
     */
    public DefaultExecutor(int x, int y)
    {
        initialize(x, y);
    }

    /**
     * Функция, возвращающая координаты исполнителя.
     * @return Возвращает координаты исполнителя.
     */
    @Override
    public Point getCoords()
    {
        return coords;
    }

    /**
     * Функция, перемещающая исполнителя на переданное смещение.
     * @param dx смещение по оси x.
     * @param dy смещение по оси y.
     */
    @Override
    public void move(int dx, int dy)
    {
        coords.shift(dx, dy);
    }

    /**
     * Функция, проверяющая, находится ли исполнитель в режиме рисования.
     * @return true, если включен режим рисования; false, если выключен.
     */
    @Override
    public boolean isDraw()
    {
        return isDraw;
    }

    /**
     * Функция, устанавливающая режим рисования исполнителю.
     * @param drawFlag true - исполнитель включает режим рисования; false - исполнитель выключает режим рисования.
     */
    @Override
    public void setDraw(boolean drawFlag)
    {
        this.isDraw = drawFlag;
    }
}
