package login;
public enum option
{
    Admin,  Guest;

    private option() {}

    public String value()
    {
        return name();
    }

    public static option fromvalue(String v)
    {
        return valueOf(v);
    }
}