package ru.nsu.commands;

public class TeleportCommand implements Command
{
    static
    {
        System.out.println("Class TeleportCommand loaded");
    }
    public TeleportCommand()
    {
        System.out.println("TeleportCommand constructor is working");
    }

    @Override
    public void execute()
    {
        System.out.println("TeleportCommand is executing");
    }
}
