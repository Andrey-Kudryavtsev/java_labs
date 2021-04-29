package ru.nsu.kudryavtsev.andrey.inputHandler;

/**
 * Интерфейс обработчика ввода.
 */
public interface InputHandler
{
    /**
     * Функция, обрабатывающая ввод.
     * @param input строка, подлежащая обработке.
     * @return Возвращает строку аргументов.
     */
    String[] handle(String input);
}
