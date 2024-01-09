package lk.ijse.RMMS.dao.custom;

import lk.ijse.RMMS.dao.CrudDAO;
import lk.ijse.RMMS.entity.Supplier;

import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier> {

//    ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException ;
//
//    boolean save(Supplier entity) throws SQLException, ClassNotFoundException ;
//
//    boolean update (Supplier entity) throws SQLException, ClassNotFoundException ;
//
//    boolean exist (String su_id) throws SQLException, ClassNotFoundException;
//
//    void delete (String su_id) throws SQLException, ClassNotFoundException;
//
//    String generateID() throws SQLException, ClassNotFoundException;
//
//    Supplier search (String su_id) throws SQLException, ClassNotFoundException;

  void   JRDesignQuery() throws SQLException, ClassNotFoundException;
}
