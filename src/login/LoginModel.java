package login;

import database.dbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginModel {

    private boolean f = false;
    public LoginModel()
    {

        try
        {
            f =  dbUtil.dbConnect();
            System.out.println(f);
        }
        catch (SQLException e)
        {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, e);
        }

        if(!f)
        {
            System.exit(1);// finalize?
        }

    }

    public boolean isDbConnected()
    {
        return f;
    }

    public boolean isLogin(User user) throws SQLException
    {
        String selectStmt = "SELECT * FROM login WHERE username = '" + user.getUsername() +"' and type = '" + user.getType() +"' and password = '"+ user.getPassword() + "'";
        try
        {
            ResultSet rsLogin = dbUtil.dbExecuteQuery(selectStmt);
            boolean f2 = rsLogin.next();
            System.out.println("In isLogin : " + f2);
            return f2;
        }
        catch (SQLException | ClassNotFoundException e)
        {
            return false;
        }

    }


}
