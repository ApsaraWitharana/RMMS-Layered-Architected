package lk.ijse.D.dao.custom.impl;

import lk.ijse.D.dao.SQLUtil;
import lk.ijse.D.dao.custom.OrderDAO;
import lk.ijse.D.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
   public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
   public boolean save (Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Orders (order_id,cu_id,dueDate) VALUES (?,?,?)"
                ,entity.getOrder_id(),entity.getCu_id(),entity.getDueDte());
    }

    @Override
   public boolean update (Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
   public boolean exist (String order_id) throws SQLException, ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT order_id FROM Orders WHERE order_id=?",order_id);
        return rst.next();
    }

    @Override
   public void delete (String order_id) throws SQLException, ClassNotFoundException{

    }

    @Override
  public   String generateID() throws SQLException, ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT order_id FROM Orders ORDER BY order_id DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d",(Integer.parseInt(rst.getString("order_id").replace("OID-",""))+1)):"OID-001";
    }

    @Override
  public   Order search (String order_id) throws SQLException, ClassNotFoundException{
       return null;
    }

}
