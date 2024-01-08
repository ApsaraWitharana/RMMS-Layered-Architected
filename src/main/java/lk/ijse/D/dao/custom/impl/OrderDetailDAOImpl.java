package lk.ijse.D.dao.custom.impl;

import lk.ijse.D.dao.SQLUtil;
import lk.ijse.D.dao.custom.OrderDetailsDAO;
import lk.ijse.D.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailsDAO {

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
       return null;
    }

    @Override
    public boolean save (OrderDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO order_details (order_id,item_code,unit_price,qty) VALUES (?,?,?,?)",entity.getOrder_id(),entity.getItem_code(),entity.getUnit_price(),entity.getQty());
    }

    @Override
  public   boolean update (OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
   public boolean exist (String order_id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
   public void delete (String order_id) throws SQLException, ClassNotFoundException{

    }

    @Override
   public String generateID() throws SQLException, ClassNotFoundException{
        return null;
    }

    @Override
   public OrderDetail search (String order_id) throws SQLException, ClassNotFoundException{
        return null;
    }

}
