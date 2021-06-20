package ru.nsu.kudryavtsev.andrey.inputHandler;

public class InputHandler
{
    private static boolean isCorrect(String input)
    {
        return (input.matches("PLANT")               ||
                input.matches("MOVE [LRUD]")         ||
                input.matches("PLAY")                ||
                input.matches("ABOUT")               ||
                input.matches("SCORE")               ||
                input.matches("MENU")                ||
                input.matches("EXIT")                ||
                input.matches("NAME [A-Z, a-z, 0-9]+"));
    }

    public static String[] handle(String input)
    {
        if (!isCorrect(input))
            return null;
        return input.split(" ");
    }
}
