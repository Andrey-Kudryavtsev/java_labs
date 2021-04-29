package ru.nsu.kudryavtsev.andrey.point;


/**
 * Класс двумерной точки.
 */
public class Point
{
    /** Координата x. */
    private int x;
    /** Координата y. */
    private int y;

    /**
     * Конструктор - создание нового объекта.
     * @param x координата x.
     * @param y координата y.
     */
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Конструктор - создание нового объекта.
     * @param other точка, координаты которой взяты за основу новой точки.
     */
    public Point(Point other)
    {
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * Функция, возвращающая координату по ее номеру.
     * 0 - x, 1 - y, иначе - выбрасывает исключение.
     * @param i номер координаты.
     * @return Возвращает запрошенную координату: 0 - x, 1 - y.
     * @throws IllegalArgumentException если в качестве номера передано не 0 или 1.
     */
    public int component(int i) throws IllegalArgumentException
    {
        return switch (i) {
            case 0 -> x;
            case 1 -> y;
            default -> throw new IllegalArgumentException();
        };
    }

    /**
     * Функция, измерающая манхэттенское расстояние между точками.
     * @param p другая точка.
     * @return Возвращает манхэттенское расстояние между текущей точкой и переданной.
     */
    public int manhattanDistance(Point p)
    {
        if (p == this) return 0;
        return Math.abs(x - p.component(0)) + Math.abs(y - p.component(1));
    }

    /**
     * Функция, изменяющая координаты точки на указанный сдвиг.
     * @param dx сдвиг по оси x.
     * @param dy сдвиг по оси y.
     */
    public void shift(int dx, int dy)
    {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString()
    {
        return ("(" + x + ", " + y + ")");
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return p.component(0) == x && p.component(1) == y;
    }

    /**
     * Функция сравнения двух точек.
     * @param p другая точка.
     * @return Возвращает: -1 - текущая точка меньше; 0 - точки равны; 1 - текущая точка больше.
     * @throws NullPointerException если p == null.
     */
    public int compareTo(Point p) throws NullPointerException
    {
        if (p == this) return 0;
        if (p == null) throw new NullPointerException();
        return x != p.component(0) ? Integer.compare(x, p.component(0)) : Integer.compare(y, p.component(1));
    }
}
