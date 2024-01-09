package lk.ijse.D.dao.custom;

import lk.ijse.D.dao.CrudDAO;
import lk.ijse.D.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

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
