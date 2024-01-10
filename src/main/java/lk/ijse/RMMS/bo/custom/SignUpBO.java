package lk.ijse.RMMS.bo.custom;

import lk.ijse.RMMS.dto.LoginDTO;
import lk.ijse.RMMS.bo.SuperBO;
import lk.ijse.RMMS.entity.Login;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {

    boolean saveUser(LoginDTO dto) throws SQLException, ClassNotFoundException ;

    LoginDTO searchUser(String user_name) throws SQLException, ClassNotFoundException ;

    boolean isCurrectUser(Login entity) throws SQLException, ClassNotFoundException ;

    boolean updateUser (Login dto) throws SQLException, ClassNotFoundException ;


    boolean isCurrectUser(String user_name, String email, String password) throws SQLException, ClassNotFoundException;
}
