package lk.ijse.D.dao.custom;

import lk.ijse.D.dao.CrudDAO;
import lk.ijse.D.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee> {

//    ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException ;
//
//    boolean save (Employee entity) throws SQLException, ClassNotFoundException ;
//
//    boolean update (Employee entity) throws SQLException, ClassNotFoundException ;
//
//    boolean exist (String emp_id) throws SQLException, ClassNotFoundException;
//
//    void delete (String emp_id) throws SQLException, ClassNotFoundException;
//
//    String generateID() throws SQLException, ClassNotFoundException;
//
//    Employee search (String emp_id) throws SQLException, ClassNotFoundException;

  void   RDesignQuery() throws SQLException, ClassNotFoundException;
}
