package lk.ijse.RMMS.dao.custom.impl;

import lk.ijse.RMMS.dao.SQLUtil;
import lk.ijse.RMMS.dao.custom.OrderDetailsDAO;
import lk.ijse.RMMS.entity.OrderDetail;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

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

    @Override
    public void JRDesignQuery() throws SQLException, ClassNotFoundException {

        SQLUtil.execute("select * from order_details\\n\" +\n" +
                "                \"left join\\n\" +\n" +
                "                \"orders \\n\" +\n" +
                "                \"on order_details. order_id = orders.order_id;");
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
//        jrDesignQuery.setText("select * from order_details\n" +
//                "left join\n" +
//                "orders \n" +
//                "on order_details. order_id = orders.order_id;");
        JasperDesign load = null;
        load.setQuery(jrDesignQuery);

    }
}
