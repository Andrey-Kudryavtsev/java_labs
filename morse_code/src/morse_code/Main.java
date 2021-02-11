package morse_code;

import java.io.*;
import java.util.Scanner;
import morse_code.command.ICommand;

public class Main
{
    private static void loadMorseAlphabet(MorseAlphabet morseAlphabet)
    {
        FileReader reader = null;
        try
        {
            reader = new FileReader("src/in/morse_alphabet.txt");
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                Character letter = line.charAt(0);
                String code = line.substring(1);

                morseAlphabet.put(letter, code);
            }
            morseAlphabet.put('\r', "\r");
            morseAlphabet.put('\n', "\n");
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
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        MorseAlphabet morseAlphabet = new MorseAlphabet();
        loadMorseAlphabet(morseAlphabet);

        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        System.out.println("Write:\n\t\"encode *FILENAME*\" - encode a file\n\t\"decode *FILENAME*\" - decode a file");
        String input = scanner.nextLine();

        try
        {
            ICommand command = inputHandler.handleInput(input);
            command.execute(morseAlphabet);
        } catch (MyException e)
        {
            e.what();
        }
    }
}
