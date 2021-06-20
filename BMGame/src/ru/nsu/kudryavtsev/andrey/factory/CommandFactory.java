package ru.nsu.kudryavtsev.andrey.factory;

import ru.nsu.kudryavtsev.andrey.commands.Command;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;

public class CommandFactory
{
    private final Properties commandPaths = new Properties();
    private final HashMap<String, Command> commands = new HashMap<>();

    public CommandFactory(String filename) throws IOException
    {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        InputStream file = cl.getResourceAsStream(filename);
        if (file == null)
        {
            throw new IOException("no such file");
        }
        commandPaths.load(file);
        file.close();
    }

    public synchronized Command createCommand(String commandName)
    {
        Command command = commands.get(commandName);
        if (command == null)
        {
            String commandPath = commandPaths.getProperty(commandName);
            if (commandPath == null)
            {
                System.err.println("Class associated with " + commandName + " not found in configuration file");
                return null;
            }

            try
            {
                Class<?> cls = Class.forName(commandPath);
                command = (Command) cls.getDeclaredConstructor().newInstance();
                commands.put(commandName, command);
            } catch (ClassNotFoundException e)
            {
                System.err.println("Class associated with " + commandName + " not found");
                e.printStackTrace();
            } catch (IllegalAccessException | InstantiationException e)
            {
                System.err.println("Class associated with " + commandName + " not initialized");
                e.printStackTrace();
            } catch (NoSuchMethodException e)
            {
                System.err.println("Constructor of the class associated with " + commandName + " not found");
                e.printStackTrace();
            } catch (InvocationTargetException e)
            {
                System.err.println("Constructor of the class associated with " + commandName + " can't be invoked");
                e.printStackTrace();
            }
        }
        return command;
    }
}
