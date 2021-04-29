package ru.nsu.kudryavtsev.andrey.FileLogger;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;


/**
 * Класс файлового логгера.
 */
public class FileLogger
{
    /**
     * Функция настройки логгера.
     * @throws IOException если невозможно открыть файл с нужным именем.
     */
    static public void setup() throws IOException
    {
        FileHandler fileHandler = new FileHandler("logging");
        SimpleFormatter formatter = new SimpleFormatter();

        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        logger.setLevel(Level.INFO);
        fileHandler.setFormatter(formatter);
        logger.addHandler(fileHandler);
    }
}
