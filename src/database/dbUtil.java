package database;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;

public class dbUtil
{
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static Connection conn = null;
    private static final String connStr = "jdbc:sqlite:C:\\Users\\HP\\Desktop\\Dattebayo!!\\OOPS PROJECT\\InnLogix\\InnLogix.sqlite";

    public static boolean dbConnect() throws SQLException
    {
        boolean isConn = false;
        try
        {
            Class.forName(JDBC_DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();

        }

        System.out.println("Oracle JDBC Driver Registered!");

        try
        {
            conn = DriverManager.getConnection(connStr);
            isConn = true;
            System.out.println("Connected Driver: "+ isConn);
        }
        catch (SQLException e)
        {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();

        }
        return isConn;
    }


    public static void dbDisconnect() throws SQLException
    {
        try
        {
            if (conn != null && !conn.isClosed())
            {
                conn.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException
    {

        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try
        {
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(queryStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        }
        catch (SQLException e)
        {
            System.out.println("Problem occurred at executeQuery operation : " + e);

        }
        finally
        {
            if (resultSet != null)
            {
                resultSet.close();
            }
            if (stmt != null)
            {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException
    {
        Statement stmt = null;
        try
        {
            dbConnect();

            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        }
        catch (SQLException e)
        {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
        }
        finally
        {
            if (stmt != null)
            {
                stmt.close();
            }
            dbDisconnect();
        }
    }
}

