package ru.nsu.commands;

public class DrawCommand implements Command
{
    static
    {
        System.out.println("Class DrawCommand loaded");
    }
    public DrawCommand()
    {
        System.out.println("DrawCommand constructor is working");
    }

    @Override
    public void execute()
    {
        System.out.println("DrawCommand is executing");
    }
}
