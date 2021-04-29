package ru.nsu.kudryavtsev.andrey.executor;

import ru.nsu.kudryavtsev.andrey.point.Point;

/**
 * Интерфейс исполнителя.
 */
public interface Executor
{
    /**
     * Функция инициализации исполнителя.
     * @param x координата x.
     * @param y координата y.
     */
    void initialize(int x, int y);

    /**
     * Функция, возвращающая координаты исполнителя.
     * @return Возвращает координаты исполнителя.
     */
    Point getCoords();

    /**
     * Функция, перемещающая исполнителя на переданное смещение.
     * @param dx смещение по оси x.
     * @param dy смещение по оси y.
     */
    void move(int dx, int dy);

    /**
     * Функция, проверяющая, находится ли исполнитель в режиме рисования.
     * @return true, если включен режим рисования; false, если выключен.
     */
    boolean isDraw();

    /**
     * Функция, устанавливающая режим рисования исполнителю.
     * @param drawFlag true - исполнитель включает режим рисования; false - исполнитель выключает режим рисования.
     */
    void setDraw(boolean drawFlag);
}
