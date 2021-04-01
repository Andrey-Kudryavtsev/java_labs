package ru.nsu;

import ru.nsu.commands.Command;
import ru.nsu.factory.DefaultCommandFactory;
import ru.nsu.input_handler.DefaultInputHandler;
import ru.nsu.input_handler.InputHandler;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new DefaultInputHandler();
        DefaultCommandFactory factory;
        try
        {
            factory = new DefaultCommandFactory("config");
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        while (true)
        {
            System.out.println("Enter the command:");
            String input = scanner.nextLine();
            if (input.equals("EXIT")) break;
            CommandTypes type = inputHandler.handle(input);
            if (type == null)
            {
                System.out.println("Wrong input, try again");
                continue;
            }
            Command command = factory.createCommand(type);
            command.execute();
        }
    }
}
