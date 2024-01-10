package lk.ijse.RMMS.bo.custom.impl;

import lk.ijse.RMMS.dto.LoginDTO;
import lk.ijse.RMMS.bo.custom.SignUpBO;
import lk.ijse.RMMS.dao.DAOFactory;
import lk.ijse.RMMS.dao.custom.SignUpDAO;
import lk.ijse.RMMS.entity.Login;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {

    SignUpDAO signUpDAO = (SignUpDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SIGNUP);
    @Override
    public boolean saveUser(LoginDTO dto) throws SQLException, ClassNotFoundException {
        return signUpDAO.save(new Login(dto.getName(),dto.getPassword(),dto.getEmail()));
    }

    @Override
    public LoginDTO searchUser(String user_name) throws SQLException, ClassNotFoundException {
        Login login = (Login) signUpDAO.search(user_name);
        return new LoginDTO(login.getName(),login.getPassword(),login.getEmail());
    }

    @Override
    public boolean isCurrectUser(Login entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateUser(Login dto) throws SQLException, ClassNotFoundException {
        return signUpDAO.update(new Login(dto.getName(),dto.getPassword(),dto.getEmail()));
    }

    @Override
    public boolean isCurrectUser(String user_name, String email, String password) throws SQLException, ClassNotFoundException {

        return signUpDAO.isCurrect(new Login(user_name,email,password));
    }
}
