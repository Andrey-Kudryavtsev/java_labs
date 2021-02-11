package morse_code.command;

import morse_code.Letter;
import morse_code.MorseAlphabet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

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
            writer = new FileWriter("src/out/out_decode.txt");
            int spaceAmount = 0;
            HashSet<Letter> set = new HashSet<>();
            StringBuilder word = new StringBuilder();
            StringBuilder code = new StringBuilder();
            State state = State.READ_CODE;
            while (reader.ready())
            {
                char symbol = (char) reader.read();
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
                        Character letter = morseAlphabet.decode(code.toString());
                        word.append(letter);
                        if (Character.isLetter(letter)) set.add(new Letter(letter));

                        code.setLength(0);
                        code.append(symbol);

                        spaceAmount = 0;
                        state = State.READ_CODE;
                    } else if (spaceAmount == 3) // если три пробела, то считали слово, записываем его в файл
                    {
                        Character letter = morseAlphabet.decode(code.toString());
                        word.append(letter);
                        if (Character.isLetter(letter)) set.add(new Letter(letter));

                        writer.write(word.toString() + ' ');
                        word.setLength(0);

                        code.setLength(0);
                        code.append(symbol);

                        spaceAmount = 0;
                        state = State.READ_CODE;
                    }
                }
            }
            Character letter = morseAlphabet.decode(code.toString());
            word.append(letter);
            if (Character.isLetter(letter)) set.add(new Letter(letter));

            writer.write(word.toString() + ' ');
            ICommand.writeFreqToFile(set, "freq_decode.txt");
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
