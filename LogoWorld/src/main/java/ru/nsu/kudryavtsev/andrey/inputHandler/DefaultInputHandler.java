package ru.nsu.kudryavtsev.andrey.inputHandler;

/**
 * Класс обработчика ввода.
 */
public class DefaultInputHandler implements InputHandler
{
    /**
     * Функция, проверяющая ввод на соответствие определенным шаблонам.
     * @param input строка, подлежащая проверке.
     * @return Возвращает true, если строка удовлетворяет какому-либо шаблону; false, если не удовлетворяет.
     */
    private boolean isCorrect(String input)
    {
        return (input.matches("INIT [1-9][0-9]* [1-9][0-9]* [0-9]+ [0-9]+") ||
                input.matches("MOVE [LRUD] [0-9]+")                         ||
                input.matches("DRAW")                                       ||
                input.matches("WARD")                                       ||
                input.matches("TELEPORT [0-9]+ [0-9]+")                     ||
                input.matches("EXIT")                                       );
    }

    /**
     * Функция, обрабатывающая ввод.
     * @param input строка, подлежащая обработке.
     * @return Возвращает строку аргументов, если ввод корректный; null, если не корректный.
     */
    @Override
    public String[] handle(String input)
    {
        if (!isCorrect(input))
        {
            return null;
        }
        return input.split(" ");
    }
}
