package lk.ijse.D.dao.custom.impl;

import lk.ijse.D.dao.SQLUtil;
import lk.ijse.D.dao.custom.SupplierOrderDAO;
import lk.ijse.D.entity.SupplierOrder;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {

    @Override
   public ArrayList<SupplierOrder> getAll() throws SQLException, ClassNotFoundException {
          return null;
    }

    @Override
   public boolean save (SupplierOrder entity) throws SQLException, ClassNotFoundException {
         return SQLUtil.execute("INSERT INTO supplier_order (order_id,su_id,dueDate) VALUES (?,?,?)", entity.getOrder_id(),entity.getSu_id(),entity.getDueDte());
   }

   @Override
 public  boolean update (SupplierOrder dto) throws SQLException, ClassNotFoundException {
       return false;
 }

 @Override
   public boolean exist (String order_id) throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT order_id FROM supplier_order WHERE  order_id=?",order_id);
       return rst.next();
   }

   @Override
  public   void delete (String order_id) throws SQLException, ClassNotFoundException {

    }

    @Override
   public String generateID() throws SQLException, ClassNotFoundException {

         ResultSet rst = SQLUtil.execute("SELECT order_id FROM supplier_order ORDER BY order_id DESC LIMIT 1;");
         return rst.next()? String.format("SOID-%03d",(Integer.parseInt(rst.getString("order_id").replace("SOID-",""))+1)):"SOID-001";
   }


   @Override
   public SupplierOrder search (String order_id) throws SQLException, ClassNotFoundException{
        return null;
    }
}
