package ru.nsu.kudryavtsev.andrey.controller;

import ru.nsu.kudryavtsev.andrey.commands.Command;
import ru.nsu.kudryavtsev.andrey.commands.Task;
import ru.nsu.kudryavtsev.andrey.factory.CommandFactory;
import ru.nsu.kudryavtsev.andrey.inputHandler.InputHandler;
import ru.nsu.kudryavtsev.andrey.model.GameModel;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleController implements Controller
{
    private final int ownerID;
    private final GameModel model;
    private final CommandFactory commandFactory;

    public ConsoleController(int ownerID, GameModel model, CommandFactory commandFactory) throws IOException
    {
        this.ownerID = ownerID;
        this.model = model;
        this.commandFactory = commandFactory;
    }

    @Override
    public void waitForInput()
    {
        Scanner scanner = new Scanner(System.in);
        while (model.isRunning())
        {
            String input = scanner.nextLine();
            String[] argList = InputHandler.handle(input);
            if (argList == null)
                continue;
            Command command = commandFactory.createCommand(argList[0]);
            if (command == null)
                continue;
            Task task = new Task(ownerID, command, argList);
            model.offerUserInput(task);
        }
    }

//    @Override
//    public void addTask(Task task)
//    {
//
//    }
}
