package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConn
{
//    private static final String USERNAME = "dbuser";
//    private static final String PASSWORD = "dbpassword";
    private static final String SCONN = "jdbc:sqlite:/home/gaurabdg/Workspace/CS_F213/HotelManagementSystem/InnLogix"; // change path accordingly

    public static Connection getConnection() throws SQLException
    {
        try
        {

            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SCONN);
        }
        catch (ClassNotFoundException|SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
