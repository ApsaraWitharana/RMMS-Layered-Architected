package lk.ijse.RMMS.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection dbConnection;
    private Connection connection;

    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ricemilling",
                "root",
                "ijse@2001"
        );
    }

//    public static DbConnection getInstance() throws SQLException {
//        return (null == dbConnection) ? dbConnection = new DbConnection() :dbConnection;
//
//    }

    public static DbConnection getDbConnection() throws SQLException ,ClassNotFoundException{
        return dbConnection == null ? dbConnection = new DbConnection() :dbConnection;
    }


    public Connection getConnection(){
        return connection;
    }

}
