package lk.ijse.RMMS.dao.custom;

import lk.ijse.RMMS.dao.SuperDAO;
import lk.ijse.RMMS.entity.Login;

import java.sql.SQLException;

public interface SignUpDAO extends SuperDAO {

    boolean save(Login entity) throws SQLException, ClassNotFoundException ;

    Login search(String user_name) throws SQLException, ClassNotFoundException ;

   // boolean isCurrect(Login entity) throws SQLException, ClassNotFoundException ;

    boolean update (Login entity) throws SQLException, ClassNotFoundException ;


    boolean isCurrect(Login entity) throws SQLException, ClassNotFoundException;


    boolean isCurrect(String user_name, String email, String password) throws SQLException, ClassNotFoundException;
}
