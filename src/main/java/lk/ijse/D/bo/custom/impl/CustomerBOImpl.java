package lk.ijse.D.bo.custom.impl;

import lk.ijse.D.DTO.CustomerDTO;
import lk.ijse.D.bo.custom.CustomerBO;
import lk.ijse.D.dao.DAOFactory;
import lk.ijse.D.dao.custom.CustomerDAO;
import lk.ijse.D.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.D.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    //CustomerDAO customerDAO = (CustomerDAO)
CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

@Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {

       ArrayList<Customer>customers = customerDAO.getAll();
       ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
       for (Customer customer : customers){
           customerDTOS.add(new CustomerDTO(customer.getCu_id(),customer.getCu_name(),customer.getAddress(),customer.getCu_contact(),customer.getCu_email()));

       }
       return customerDTOS;
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCu_id(),dto.getCu_name(),dto.getAddress(),dto.getCu_contact(),dto.getCu_email()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCu_id(),dto.getCu_name(),dto.getAddress(),dto.getCu_contact(),dto.getCu_email()));
    }

    @Override

    public boolean existCustomer(String cu_id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(cu_id);
    }

    @Override
    public void deleteCustomer(String cu_id) throws SQLException, ClassNotFoundException {
        customerDAO.delete(cu_id);

    }

   @Override
    public String generateCustomerID() throws SQLException, ClassNotFoundException {
       return customerDAO.generateID();
    }

   @Override
    public CustomerDTO searchCustomer(String cu_id) throws SQLException, ClassNotFoundException {
       Customer customer = customerDAO.search(cu_id);
       CustomerDTO customerDTO = new CustomerDTO(customer.getCu_id(),customer.getCu_name(),customer.getAddress(),customer.getCu_contact(),customer.getCu_email());
       return customerDTO;
    }

    @Override
    public void JRDesignQuery() {

    }

}
