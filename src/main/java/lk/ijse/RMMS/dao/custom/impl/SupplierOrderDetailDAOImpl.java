package lk.ijse.RMMS.dao.custom.impl;

import lk.ijse.RMMS.dao.SQLUtil;
import lk.ijse.RMMS.dao.custom.SupplierOrderDetailDAO;
import lk.ijse.RMMS.entity.SupplierOrderDetail;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

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


    @Override
    public void JRDesignQuery() throws SQLException, ClassNotFoundException {
        SQLUtil.execute("select * from supplier_lord_details\\n\" +\n" +
                "  \"left join\\n\" +\n" +
                "  \"supplier_order\\n\" +\n" +
                "  \"on supplier_lord_details . order_id = supplier_order.order_id; ");
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
//                jrDesignQuery.setText("select * from supplier_lord_details\n" +
//                        "left join\n" +
//                        "supplier_order\n" +
//                        "on supplier_lord_details . order_id = supplier_order.order_id; ");
        JasperDesign load = null;
        load.setQuery(jrDesignQuery);
    }
}
