package morse_code;

import java.util.Objects;

public class Letter implements Comparable<Object>
{
    private final char ch;
    private int freq;

    public Letter(char ch)
    {
        this.ch = ch;
        freq = 1;
    }

    public void increaseFreq()
    {
        freq++;
    }

    @Override
    public String toString()
    {
        return (ch + " : " + freq);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letter other = (Letter) o;
        if (ch == other.ch) other.freq++;
        return ch == other.ch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ch);
    }

    @Override
    public int compareTo(Object o) throws NullPointerException, ClassCastException
    {
        if (this == o) return 0;
        if (o == null) throw new NullPointerException();
        if (getClass() != o.getClass()) throw new ClassCastException();
        Letter other = (Letter) o;
        return Integer.compare(freq, other.freq);
    }
}
