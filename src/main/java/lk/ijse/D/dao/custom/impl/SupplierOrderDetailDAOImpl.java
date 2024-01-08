package lk.ijse.D.dao.custom.impl;

import lk.ijse.D.dao.SQLUtil;
import lk.ijse.D.dao.custom.SupplierOrderDetailDAO;
import lk.ijse.D.entity.SupplierOrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailDAOImpl implements SupplierOrderDetailDAO {

    @Override
      public ArrayList<SupplierOrderDetail> getAll() throws SQLException, ClassNotFoundException {
          return null;
      }

    @Override
    public boolean save (SupplierOrderDetail entity) throws SQLException, ClassNotFoundException {
          return SQLUtil.execute("INSERT INTO supplier_lord_details (order_id,item_code,qty,unit_price) VALUES (?,?,?,?)",entity.getOrder_id(),entity.getItem_code(),entity.getQty(),entity.getUnit_price());

    }

    @Override
   public boolean update (SupplierOrderDetail entity) throws SQLException, ClassNotFoundException {
         return false;

    }
  @Override
  public   boolean exist (String order_id) throws SQLException, ClassNotFoundException{
          return false;
  }

  @Override
  public   void delete (String order_id) throws SQLException, ClassNotFoundException{

  }

  @Override
  public   String generateID() throws SQLException, ClassNotFoundException{
          return null;
  }

  @Override
  public   SupplierOrderDetail search (String order_id) throws SQLException, ClassNotFoundException{
          return null;
  }

}
