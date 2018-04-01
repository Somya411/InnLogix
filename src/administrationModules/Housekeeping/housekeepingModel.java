package administrationModules.Housekeeping;

import database.dbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class housekeepingModel {

        private boolean f = false;
        public housekeepingModel()
        {

            try
            {
                f =  dbUtil.dbConnect();
                System.out.println(f);
            }
            catch (SQLException e)
            {
                Logger.getLogger(housekeepingModel.class.getName()).log(Level.SEVERE, null, e);
            }

            if(!f)
            {
                System.exit(1);// finalize?
            }

        }

        public boolean isDbConnected() {

            return f;
        }

        public boolean isRecord(Record record) throws SQLException
        {
            String selectStmt = "SELECT * FROM housekeeping WHERE Room No. = '" + record.getRoomNo() +"'";
            try
            {
                ResultSet rsHousekeeping = dbUtil.dbExecuteQuery(selectStmt);
                boolean f2 = rsHousekeeping.next();
                System.out.println("In isRecord : " + f2);
                return f2;
            }
            catch (SQLException | ClassNotFoundException e)
            {
                return false;
            }

        }


    }


