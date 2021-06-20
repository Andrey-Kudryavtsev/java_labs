package ru.nsu.kudryavtsev.andrey.controller;

import ru.nsu.kudryavtsev.andrey.commands.Command;
import ru.nsu.kudryavtsev.andrey.commands.Task;
import ru.nsu.kudryavtsev.andrey.factory.CommandFactory;
import ru.nsu.kudryavtsev.andrey.inputHandler.InputHandler;
import ru.nsu.kudryavtsev.andrey.model.GameModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphicController implements Controller, KeyListener, ActionListener
{
    private final int ownerID;
    private final GameModel model;
    private final CommandFactory commandFactory;

    public GraphicController(int ownerID, GameModel model, CommandFactory commandFactory)
    {
        this.ownerID = ownerID;
        this.model = model;
        this.commandFactory = commandFactory;
    }

    @Override
    public void waitForInput()
    {
//        while (model.isRunning()){}
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W -> {
                String[] argList = {"MOVE", "U"};
                Command command = commandFactory.createCommand("MOVE");
                Task task = new Task(ownerID, command, argList);
                model.offerUserInput(task);
            }
            case KeyEvent.VK_D -> {
                String[] argList = {"MOVE", "R"};
                Command command = commandFactory.createCommand("MOVE");
                Task task = new Task(ownerID, command, argList);
                model.offerUserInput(task);
            }
            case KeyEvent.VK_S -> {
                String[] argList = {"MOVE", "D"};
                Command command = commandFactory.createCommand("MOVE");
                Task task = new Task(ownerID, command, argList);
                model.offerUserInput(task);
            }
            case KeyEvent.VK_A -> {
                String[] argList = {"MOVE", "L"};
                Command command = commandFactory.createCommand("MOVE");
                Task task = new Task(ownerID, command, argList);
                model.offerUserInput(task);
            }
            case KeyEvent.VK_SPACE -> {
                String[] argList = {"PLANT"};
                Command command = commandFactory.createCommand("PLANT");
                Task task = new Task(ownerID, command, argList);
                model.offerUserInput(task);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String input = e.getActionCommand();
        String[] argList = InputHandler.handle(input);
        Command command = commandFactory.createCommand(argList[0]);
        if (command == null)
            return;
        Task task = new Task(ownerID, command, argList);
        model.offerUserInput(task);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
