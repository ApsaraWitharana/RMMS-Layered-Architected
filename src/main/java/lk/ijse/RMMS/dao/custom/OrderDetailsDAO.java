package lk.ijse.RMMS.dao.custom;

import lk.ijse.RMMS.dao.CrudDAO;
import lk.ijse.RMMS.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetail> {

//    ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException ;
//
//    boolean save (OrderDetail entity) throws SQLException, ClassNotFoundException ;
//
//    boolean update (OrderDetail entity) throws SQLException, ClassNotFoundException ;
//
//    boolean exist (String order_id) throws SQLException, ClassNotFoundException;
//
//    void delete (String order_id) throws SQLException, ClassNotFoundException;
//
//    String generateID() throws SQLException, ClassNotFoundException;
//
//    OrderDetail search (String order_id) throws SQLException, ClassNotFoundException;

   void JRDesignQuery() throws SQLException, ClassNotFoundException;
}
