package ru.nsu.kudryavtsev.andrey;

import ru.nsu.kudryavtsev.andrey.commands.Command;
import ru.nsu.kudryavtsev.andrey.executor.DefaultExecutor;
import ru.nsu.kudryavtsev.andrey.executor.Executor;
import ru.nsu.kudryavtsev.andrey.factory.CommandFactory;
import ru.nsu.kudryavtsev.andrey.factory.DefaultCommandFactory;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.field.ToroidalField;
import ru.nsu.kudryavtsev.andrey.inputHandler.DefaultInputHandler;
import ru.nsu.kudryavtsev.andrey.inputHandler.InputHandler;
import ru.nsu.kudryavtsev.andrey.FileLogger.FileLogger;
import ru.nsu.kudryavtsev.andrey.view.ConsoleView;
import ru.nsu.kudryavtsev.andrey.view.View;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Класс Main.
 */
public class Main {

    /**
     * Функция main.
     * Инициализирует обработчик ввода, фабрику команд, класс отрисовки, поле, исполнителя.
     * Содержит основной цикл: чтение ввода, обработка ввода, создание команды, исполнение команды, отрисовка поля и исполнителя.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args)
    {
        try
        {
            FileLogger.setup();
            Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

            Scanner scanner = new Scanner(System.in);
            InputHandler inputHandler = new DefaultInputHandler();
            logger.log(Level.INFO, "Create the factory");
            CommandFactory factory = new DefaultCommandFactory("config");
            View view = new ConsoleView();

            Field field = new ToroidalField(0, 0);
            Executor executor = new DefaultExecutor(0, 0);

            boolean isInitialized = false;

            while (true)
            {
                System.out.println("Enter the command:");
                logger.log(Level.INFO, "Read user input");
                String input = scanner.nextLine();
                String[] argList = inputHandler.handle(input);
                if (argList == null)
                {
                    logger.log(Level.INFO, "Wrong input");
                    System.out.println("Wrong input, try again");
                    continue;
                }
                if (argList[0].equals("EXIT"))
                {
                    logger.log(Level.INFO, "Stop the application by EXIT");
                    break;
                }
                if (!argList[0].equals("INIT") && !isInitialized)
                {
                    logger.log(Level.INFO, "Attempt to execute a command without previous INIT");
                    System.out.println("Please, initialize application with INIT first");
                    continue;
                }
                logger.log(Level.INFO, "Create the command");
                Command command = factory.createCommand(argList[0]);
                logger.log(Level.INFO, "Execute the command");
                command.execute(field, executor, argList);
                isInitialized = true;
                logger.log(Level.INFO, "Draw field in the console");
                view.draw(field, executor);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
