package morse_code.command;

import morse_code.Letter;
import morse_code.MorseAlphabet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class EncodeCommand implements ICommand
{
    private final String filename;

    public EncodeCommand(String filename)
    {
        this.filename = filename;
    }

    public void execute(MorseAlphabet morseAlphabet)
    {
        FileReader reader = null;
        FileWriter writer = null;
        try
        {
            reader = new FileReader(filename);
            Scanner scanner = new Scanner(reader);
            writer = new FileWriter("src/out/out_encode.txt");
            HashSet<Letter> stat = new HashSet<>();
            StringBuilder word = new StringBuilder();

            while (scanner.hasNextLine())
            {
                String buf = scanner.nextLine() + '\n';
                for (int i = 0; i < buf.length(); i++)
                {
                    char symbol = buf.charAt(i);
                    symbol = Character.toLowerCase(symbol);
                    if (symbol == ' ')
                    {
                        writer.write(word.toString() + "  ");
                        word.setLength(0);
                    } else
                    {
                        if (Character.isLetter(symbol)) stat.add(new Letter(symbol));
                        String code = morseAlphabet.encode(symbol);
                        word.append(code).append(' ');
                    }
                }
            }
            writer.write(word.toString().trim());
            ICommand.writeFreqToFile(stat, "freq_encode.txt");
        }
        catch (IOException e)
        {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                } catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
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
}
