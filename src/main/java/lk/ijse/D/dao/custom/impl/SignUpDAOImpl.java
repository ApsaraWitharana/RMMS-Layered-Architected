package lk.ijse.D.dao.custom.impl;

import lk.ijse.D.dao.SQLUtil;
import lk.ijse.D.dao.custom.SignUpDAO;
import lk.ijse.D.entity.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpDAOImpl implements SignUpDAO {

    @Override
   public boolean save(Login entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO users VALUES (?,?,?)",
                entity.getName(), entity.getEmail(), entity.getPassword());

    }
@Override
  public   Login search(String user_name) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM users WHERE user_name=?",user_name );
        rst.next();
        return new Login ( user_name,rst.getString("email"), rst.getString("password"));

    }

//    @Override
//   public boolean isCurrect(Login entity) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute("INSERT INTO users VALUES (?,?,?)",
//                entity.getName(), entity.getEmail(), entity.getPassword());
//
//    }

    @Override
   public boolean update (Login entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE users SET user_name = ?, email= ?, WHERE password = ?",
                entity.getName(), entity.getEmail(), entity.getPassword());

    }

    @Override
    public boolean isCurrect(Login entity) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM users WHERE user_name=? , email=? , password=?"
                ,entity.getName(),entity.getEmail(),entity.getPassword() );
        rst.next();
        return true;
    }

    @Override
    public boolean isCurrect(String user_name, String email, String password) throws SQLException, ClassNotFoundException {
        ResultSet rst= SQLUtil.execute("SELECT * FROM users WHERE user_name=? && email=? && password=?",user_name,email,password);
        return rst.next();
    }

}
