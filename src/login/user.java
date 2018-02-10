package login;
public enum user
{
    Admin,  Guest;

    private user() {}

    public String value()
    {
        return name();
    }

    public static user fromvalue(String v)
    {
        return valueOf(v);
    }
}