package lk.ijse.RMMS.dao.custom;

import lk.ijse.RMMS.dao.CrudDAO;
import lk.ijse.RMMS.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
//    ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException;
//
//    boolean save(Customer entity) throws SQLException, ClassNotFoundException;
//
//    boolean update(Customer entity) throws SQLException, ClassNotFoundException;
//
//    boolean exist(String cu_id) throws SQLException, ClassNotFoundException;
//
//    void delete(String cu_id) throws SQLException, ClassNotFoundException;
//
//    String generateID() throws SQLException, ClassNotFoundException;
//
//    Customer search(String cu_id) throws SQLException, ClassNotFoundException;
void JRDesignQuery() throws SQLException, ClassNotFoundException;
}
