package lk.ijse.RMMS.bo.custom.impl;

import lk.ijse.RMMS.dto.LoginDTO;
import lk.ijse.RMMS.bo.custom.LoginBO;
import lk.ijse.RMMS.dao.DAOFactory;
import lk.ijse.RMMS.dao.custom.LoginDAO;
import lk.ijse.RMMS.entity.Login;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);
    @Override
    public boolean saveUser(LoginDTO dto) throws SQLException, ClassNotFoundException {
        return loginDAO.save(new Login(dto.getName(),dto.getEmail(),dto.getPassword()));
    }

    @Override
    public LoginDTO searchUser(String user_name) throws SQLException, ClassNotFoundException {

        Login login = (Login) loginDAO.search(user_name);
        return new LoginDTO(login.getName(),login.getPassword(),login.getEmail());
    }

    @Override
    public boolean isCurrectUser(String user_name, String password) throws SQLException, ClassNotFoundException {
        return false;
    }
}
