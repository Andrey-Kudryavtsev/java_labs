package ru.nsu.factory;

import ru.nsu.CommandTypes;
import ru.nsu.commands.Command;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.HashMap;

public class DefaultCommandFactory implements CommandFactory
{
    private final Properties commandPath = new Properties();
    private final HashMap<CommandTypes, Command> commands = new HashMap<>();

    public DefaultCommandFactory(String filename) throws IOException
    {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        try (InputStream file = cl.getResourceAsStream(filename))
        {
            commandPath.load(file);
        }
    }

    @Override
    public Command createCommand(CommandTypes type) // TODO: почитать про загрузку классов, понять, как передавать параметры в команду
    {
        if (!commands.containsKey(type))
        {
            if (!commandPath.containsKey(type.name()))
            {
                System.err.println("Class associated with " + type.name() + " not found in configuration file");
                return null;
            }
            try
            {
                Class cls = Class.forName(commandPath.getProperty(type.name()));
                Command command = (Command) cls.getDeclaredConstructor().newInstance();
                commands.put(type, command);
            } catch (ClassNotFoundException e)
            {
                System.err.println("Class associated with " + type.name() + " not found");
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException | InstantiationException e)
            {
                System.err.println("Class associated with " + type.name() + " not initialized");
                e.printStackTrace();
                return null;
            } catch (NoSuchMethodException e)
            {
                System.err.println("Constructor of the class associated with " + type.name() + " not found");
                e.printStackTrace();
                return null;
            } catch (InvocationTargetException e)
            {
                System.err.println("Constructor of the class associated with " + type.name() + " can't be invoked");
                e.printStackTrace();
                return null;
            }
        }
        return commands.get(type);
    }
}
