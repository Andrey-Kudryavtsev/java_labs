package ru.nsu.commands;

public class WardCommand implements Command
{
    static
    {
        System.out.println("Class WardCommand loaded");
    }
    public WardCommand()
    {
        System.out.println("WardCommand constructor is working");
    }

    @Override
    public void execute()
    {
        System.out.println("WardCommand is executing");
    }
}
