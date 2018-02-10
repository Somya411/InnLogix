package login;

import database.SQLiteConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginModel {
    Connection connection;

    public LoginModel()
    {
        try
        {
            this.connection = SQLiteConn.getConnection();

        }
        catch (SQLException e)
        {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, e);
        }

        if(this.connection == null)
        {
            System.exit(1);// finalize?
        }

    }

    public boolean isDbConnected()
    {
        return this.connection != null;
    }

    public boolean isLogin(String user, String pass, String type) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM login WHERE username = ? and type = ? and password = ?";
        try
        {
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, pass);

            resultSet = preparedStatement.executeQuery();
            boolean f = resultSet.next();
            System.out.println("In isLogin : " + f);
            return f;


        }
        catch (SQLException e)
        {
            return false;
        }
        finally
        {
            if (preparedStatement != null)
            {
                preparedStatement.close();
            }
            if (resultSet != null)
            {
                resultSet.close();
            }
        }
    }


}
