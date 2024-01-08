package lk.ijse.D.bo.custom.impl;

import lk.ijse.D.DTO.CustomerDTO;
import lk.ijse.D.DTO.ItemDTO;
import lk.ijse.D.DTO.OrderDetailDTO;
import lk.ijse.D.bo.custom.PlaceOrderBO;
import lk.ijse.D.dao.DAOFactory;
import lk.ijse.D.dao.custom.*;
import lk.ijse.D.dbConnection.DbConnection;
import lk.ijse.D.entity.Customer;
import lk.ijse.D.entity.Item;
import lk.ijse.D.entity.Order;
import lk.ijse.D.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
  public   boolean placeOrder(String order_id, LocalDate dueDte, String cu_id, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        // Transaction//

        Connection connection = null;
        connection = DbConnection.getDbConnection().getConnection();

        boolean b1 = orderDAO.exist(order_id);
        if (b1){
            return false;
        }

        connection.setAutoCommit(false);

        boolean b2 = orderDAO.save(new Order(order_id,dueDte,cu_id));

        if (!b2){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailDTO detail : orderDetails){
            boolean b3 = orderDetailsDAO.save(new OrderDetail(detail.getOrder_id(),detail.getItem_code(),detail.getQty(),detail.getUnit_price()));
            if (!b3){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            System.out.println(detail.getItem_code());
            ItemDTO item = findItem(detail.getItem_code());
            item.setQty(item.getQty() - detail.getQty());

            boolean b = itemDAO.update(new Item(item.getItem_code(),item.getName(),item.getDescription(),item.getUnit_price(),item.getQty()));

            if (!b){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public ItemDTO findItem(String item_code) {
        try {
            Item item = itemDAO.search(item_code);
            return new ItemDTO(item.getItem_code(),item.getName(),item.getDescription(),item.getUnit_price(),item.getQty());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item"+item_code,e);
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
        }
        return null;
    }

    @Override
    public CustomerDTO searchCustomer(String cu_id) throws SQLException, ClassNotFoundException{
        Customer customer = customerDAO.search(cu_id);
        CustomerDTO customerDTO = new CustomerDTO(customer.getCu_id(),customer.getCu_name(),customer.getAddress(),customer.getCu_contact(),customer.getCu_email());
        return customerDTO;
    }

    @Override
   public ItemDTO searchItem (String item_code) throws SQLException, ClassNotFoundException{
       Item item = (Item) itemDAO.search(item_code);
       return new ItemDTO(item.getItem_code(), item.getName(), item.getDescription(), item.getUnit_price(), item.getQty());

    }

    @Override
   public boolean existCustomer(String cu_id) throws SQLException, ClassNotFoundException{
        return customerDAO.exist(cu_id);
    }

    @Override

   public boolean existItem (String item_code) throws SQLException, ClassNotFoundException{
        return itemDAO.exist(item_code);
    }



    @Override
   public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException{
       ArrayList<Customer>customers = customerDAO.getAll();
       ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
       for (Customer customer : customers){
           customerDTOS.add(new CustomerDTO(customer.getCu_id(),customer.getCu_name(),customer.getAddress(),customer.getCu_contact(),customer.getCu_email()));

       }
       return customerDTOS;
    }

    @Override
   public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
       ArrayList<Item> items = itemDAO.getAll();
       ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
       for (Item item : items) {
           itemDTOS.add(new ItemDTO(item.getItem_code(), item.getName(), item.getDescription(), item.getUnit_price(), item.getQty()));

       }
       return itemDTOS;

    }
@Override
  public   String generateOrderID()throws SQLException, ClassNotFoundException{
        return orderDAO.generateID();

    }


}
