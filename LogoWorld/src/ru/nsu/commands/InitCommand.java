package ru.nsu.commands;

public class InitCommand implements Command
{
    static
    {
        System.out.println("Class InitCommand loaded");
    }
    public InitCommand()
    {
        System.out.println("InitCommand constructor is working");
    }

    @Override
    public void execute()
    {
        System.out.println("InitCommand is executing");
    }
}
