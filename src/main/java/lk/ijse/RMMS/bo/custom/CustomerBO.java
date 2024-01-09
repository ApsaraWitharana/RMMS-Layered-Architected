package lk.ijse.RMMS.bo.custom;

import lk.ijse.RMMS.DTO.CustomerDTO;
import lk.ijse.RMMS.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean existCustomer(String cu_id) throws SQLException, ClassNotFoundException;

    void deleteCustomer(String cu_id) throws SQLException, ClassNotFoundException;

    String generateCustomerID() throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String cu_id) throws SQLException, ClassNotFoundException;

    void JRDesignQuery();
}
