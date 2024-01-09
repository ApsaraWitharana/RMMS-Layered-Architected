package lk.ijse.RMMS.dao.custom;

import lk.ijse.RMMS.dao.CrudDAO;
import lk.ijse.RMMS.entity.SupplierOrderDetail;

import java.sql.SQLException;

public interface SupplierOrderDetailDAO extends CrudDAO<SupplierOrderDetail> {
  //  ArrayList<SupplierOrderDetail> getAll() throws SQLException, ClassNotFoundException;

    //   ArrayList<SupplierOrderDetail> getAll() throws SQLException, ClassNotFoundException ;
//
//    boolean save (SupplierOrderDetail entity) throws SQLException, ClassNotFoundException ;
//
//    boolean update (SupplierOrderDetail entity) throws SQLException, ClassNotFoundException ;
//
//    boolean exist (String order_id) throws SQLException, ClassNotFoundException;
//
//    void delete (String order_id) throws SQLException, ClassNotFoundException;
//
//    String generateID() throws SQLException, ClassNotFoundException;
//
//    SupplierOrderDetail search (String order_id) throws SQLException, ClassNotFoundException;

  void   JRDesignQuery() throws SQLException, ClassNotFoundException;
}
