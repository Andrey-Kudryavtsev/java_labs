package morse_code;

import java.util.TreeMap;

public class MorseAlphabet
{
    private final TreeMap<Character, String> encodingMap;
    private final TreeMap<String, Character> decodingMap;

    public MorseAlphabet()
    {
        encodingMap = new TreeMap<>();
        decodingMap = new TreeMap<>();
    }

    public void put(Character letter, String code)
    {
        encodingMap.put(letter, code);
        decodingMap.put(code, letter);
    }

    public String encode(Character letter)
    {
        return encodingMap.get(letter);
    }

    public Character decode(String code)
    {
        return decodingMap.get(code);
    }
}
