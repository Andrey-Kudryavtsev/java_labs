package morse_code.command;

import morse_code.Letter;
import morse_code.MorseAlphabet;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public interface ICommand
{
    static void writeFreqToFile(HashSet<Letter> set, String filename)
    {
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/out/" + filename);
            for (Letter item : set)
            {
                writer.write(item.toString() + '\n');
            }
        } catch (IOException e)
        {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } finally
        {
            if (writer != null)
            {
                try
                {
                    writer.close();
                } catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
    void execute(MorseAlphabet morseAlphabet);
}
