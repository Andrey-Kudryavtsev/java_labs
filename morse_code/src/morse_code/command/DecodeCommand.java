package morse_code.command;

import morse_code.Letter;
import morse_code.MorseAlphabet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class DecodeCommand implements ICommand
{
    private final String filename;

    private enum State
    {
        READ_CODE,
        CHECK_SPACES,
    }

    public DecodeCommand(String filename)
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
            writer = new FileWriter("src/out/out_decode.txt");
            int spaceAmount = 0;
            HashSet<Letter> stat = new HashSet<>();
            StringBuilder word = new StringBuilder();
            StringBuilder code = new StringBuilder();
            State state = State.READ_CODE;

            while(scanner.hasNextLine())
            {
                String buf = scanner.nextLine() + '\n';
                for (int i = 0; i < buf.length(); i++)
                {
                    char symbol = buf.charAt(i);
                    if (state == State.READ_CODE) // если считываем код одной буквы
                    {
                        if (symbol == '.' || symbol == '-') // если точка или тире, то добавляем символ к коду
                        {
                            code.append(symbol);
                        } else if (symbol == ' ') // если пробел, то проверяем, сколько их
                        {
                            spaceAmount++;
                            state = State.CHECK_SPACES;
                        }
                    } else if (state == State.CHECK_SPACES)
                    {
                        if (symbol == ' ')
                        {
                            spaceAmount++;
                            continue;
                        }

                        if (spaceAmount == 1) // если один пробел, то считали букву, добавляем ее к слову
                        {
                            char letter = morseAlphabet.decode(code.toString());
                            word.append(letter);
                            if (Character.isLetter(letter)) stat.add(new Letter(letter));

                            code.setLength(0);
                            code.append(symbol);

                            spaceAmount = 0;
                            state = State.READ_CODE;
                        } else if (spaceAmount == 3) // если три пробела, то считали слово, записываем его в файл
                        {
                            char letter = morseAlphabet.decode(code.toString());
                            word.append(letter);
                            if (Character.isLetter(letter)) stat.add(new Letter(letter));

                            writer.write(word.toString() + ' ');
                            word.setLength(0);

                            code.setLength(0);
                            code.append(symbol);

                            spaceAmount = 0;
                            state = State.READ_CODE;
                        }
                    }
                }
            }
            char letter = morseAlphabet.decode(code.toString());
            word.append(letter);
            if (Character.isLetter(letter)) stat.add(new Letter(letter));

            writer.write(word.toString());
            ICommand.writeFreqToFile(stat, "freq_decode.txt");
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
