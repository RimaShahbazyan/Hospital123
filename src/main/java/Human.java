import java.io.Serializable;

public abstract class Human implements Serializable {
    Human(String n, String s)
    {
        name=n;
        surname=s;
    }

    private String name;
    private String surname;

    public String getName()
    {
        return name;
    }
    public String getSurName()
    {
        return surname;
    }
}


