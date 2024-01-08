package lk.ijse.D.bo.custom;

import lk.ijse.D.DTO.LoginDTO;
import lk.ijse.D.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {

    boolean saveUser(LoginDTO dto) throws SQLException, ClassNotFoundException ;

    LoginDTO searchUser(String user_name) throws SQLException, ClassNotFoundException ;

    boolean isCurrectUser(String user_name, String password) throws SQLException, ClassNotFoundException ;

}
