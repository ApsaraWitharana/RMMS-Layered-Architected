package lk.ijse.D.dao.custom;

import lk.ijse.D.dao.SuperDAO;
import lk.ijse.D.entity.Login;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {

     boolean save(Login entity) throws SQLException, ClassNotFoundException ;

    Login search(String user_name) throws SQLException, ClassNotFoundException ;

    boolean isCurrect(String user_name, String password) throws SQLException, ClassNotFoundException ;



}
