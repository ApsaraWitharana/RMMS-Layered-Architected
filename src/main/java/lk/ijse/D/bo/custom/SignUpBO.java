package lk.ijse.D.bo.custom;

import lk.ijse.D.DTO.LoginDTO;
import lk.ijse.D.bo.SuperBO;
import lk.ijse.D.entity.Login;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {

    boolean saveUser(LoginDTO dto) throws SQLException, ClassNotFoundException ;

    LoginDTO searchUser(String user_name) throws SQLException, ClassNotFoundException ;

    boolean isCurrectUser(Login entity) throws SQLException, ClassNotFoundException ;

    boolean updateUser (Login dto) throws SQLException, ClassNotFoundException ;


    boolean isCurrectUser(String user_name, String email, String password) throws SQLException, ClassNotFoundException;
}
