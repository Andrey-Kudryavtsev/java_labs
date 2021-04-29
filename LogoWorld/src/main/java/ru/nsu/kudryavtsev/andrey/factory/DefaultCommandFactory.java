package ru.nsu.kudryavtsev.andrey.factory;

import ru.nsu.kudryavtsev.andrey.commands.Command;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Класс базовой фабрики команд.
 */
public class DefaultCommandFactory implements CommandFactory
{
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final Properties commandPaths = new Properties();
    private final HashMap<String, Command> commands = new HashMap<>();

    /**
     * Конструктор - создание нового объекта.
     * @param filename имя конфигурационного файла.
     * @throws IOException если файл с соответствующим именем недоступен.
     */
    public DefaultCommandFactory(String filename) throws IOException
    {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        logger.log(Level.INFO, "Load the config file");
        InputStream file = cl.getResourceAsStream(filename);
        if (file == null)
        {
            logger.log(Level.WARNING, "Config file not found");
            throw new IOException("no such file");
        }
        commandPaths.load(file);
        file.close();
    }

    /**
     * Функция создания команды.
     * Проверяет наличие запрошенной команды в списке уже загруженных команд - commands. Если есть, то возвращает запрошенную команду.
     * Если ее нет, то проверяет наличие пути до нее в конфигурационном файле - commandPaths.
     * Если есть, то создает запрошенную команду с помощью java reflection, добавляет ее в commands и возвращает ее же обратно.
     * Если нет, или возникла ошибка, то возвращает null.
     * @param commandName имя команды.
     * @return успех - возвращает команду, ассоциированную с commandName; неудача - возвращает null.
     */
    @Override
    public Command createCommand(String commandName)
    {
        logger.log(Level.INFO, "Check if needed command was loaded previously");
        Command command = commands.get(commandName);
        if (command == null)
        {
            logger.log(Level.INFO, "Check if needed command name exists in the config file");
            String commandPath = commandPaths.getProperty(commandName);
            if (commandPath == null)
            {
                logger.log(Level.WARNING, "Class associated with " + commandName + " not found in configuration file");
                System.err.println("Class associated with " + commandName + " not found in configuration file");
                return null;
            }

            try
            {
                Class<?> cls = Class.forName(commandPath);
                command = (Command) cls.getDeclaredConstructor().newInstance();
                logger.log(Level.INFO, "Add command to the commands map");
                commands.put(commandName, command);
            } catch (ClassNotFoundException e)
            {
                logger.log(Level.WARNING, "Class associated with " + commandName + " not found");
                System.err.println("Class associated with " + commandName + " not found");
                e.printStackTrace();
            } catch (IllegalAccessException | InstantiationException e)
            {
                logger.log(Level.WARNING, "Class associated with " + commandName + " not initialized");
                System.err.println("Class associated with " + commandName + " not initialized");
                e.printStackTrace();
            } catch (NoSuchMethodException e)
            {
                logger.log(Level.WARNING, "Constructor of the class associated with " + commandName + " not found");
                System.err.println("Constructor of the class associated with " + commandName + " not found");
                e.printStackTrace();
            } catch (InvocationTargetException e)
            {
                logger.log(Level.WARNING, "Constructor of the class associated with " + commandName + " can't be invoked");
                System.err.println("Constructor of the class associated with " + commandName + " can't be invoked");
                e.printStackTrace();
            }
        }
        return command;
    }
}
