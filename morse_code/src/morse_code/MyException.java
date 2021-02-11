package morse_code;

public class MyException extends Exception
{
    private final String msg;

    public MyException(String msg)
    {
        this.msg = msg;
    }

    public void what()
    {
        System.out.println(msg);
    }
}
