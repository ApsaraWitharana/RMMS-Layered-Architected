package lk.ijse.RMMS.bo.custom;

import lk.ijse.RMMS.dto.LoginDTO;
import lk.ijse.RMMS.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {

    boolean saveUser(LoginDTO dto) throws SQLException, ClassNotFoundException ;

    LoginDTO searchUser(String user_name) throws SQLException, ClassNotFoundException ;

    boolean isCurrectUser(String user_name, String password) throws SQLException, ClassNotFoundException ;

}
