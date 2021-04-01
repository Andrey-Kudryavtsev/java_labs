package ru.nsu.commands;

public class MoveCommand implements Command
{
    static
    {
        System.out.println("Class MoveCommand loaded");
    }
    public MoveCommand()
    {
        System.out.println("MoveCommand constructor is working");
    }

    @Override
    public void execute()
    {
        System.out.println("MoveCommand is executing");
    }
}
