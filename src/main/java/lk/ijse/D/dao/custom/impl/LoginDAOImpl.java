package lk.ijse.D.dao.custom.impl;

import lk.ijse.D.dao.SQLUtil;
import lk.ijse.D.dao.custom.LoginDAO;
import lk.ijse.D.entity.Item;
import lk.ijse.D.entity.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public boolean save(Login entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO users VALUES (?,?,?)",
                entity.getName(), entity.getEmail(), entity.getPassword());
    }

    @Override
    public Login search(String user_name) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM users WHERE user_name=?",user_name );
        rst.next();
        return new Login ( user_name,rst.getString("email"), rst.getString("password"));
    }

    @Override
    public  boolean isCurrect(String user_name, String password) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM users WHERE user_name=? && password=?", user_name, password);

        while (rst.next()) {
            String userName = rst.getString(1);
            String psw = rst.getString(2);

            //var entity = new User(userName,psw);


            if (rst.equals(null)) {
                return false;
            }
            return true;
        }
        return false;


    }
}
