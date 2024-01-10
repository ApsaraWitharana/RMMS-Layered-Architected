package lk.ijse.RMMS.bo.custom;

import lk.ijse.RMMS.dto.EmployeeDTO;
import lk.ijse.RMMS.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException ;

    boolean saveEmployee (EmployeeDTO dto) throws SQLException, ClassNotFoundException ;

    boolean updateEmployee (EmployeeDTO dto) throws SQLException, ClassNotFoundException ;

    boolean existEmployee (String emp_id) throws SQLException, ClassNotFoundException;

    void deleteEmployee (String emp_id) throws SQLException, ClassNotFoundException;

    String generateEmployeeID() throws SQLException, ClassNotFoundException;

    EmployeeDTO searchEmployee (String emp_id) throws SQLException, ClassNotFoundException;

    void   RDesignQuery() throws SQLException, ClassNotFoundException;
}
