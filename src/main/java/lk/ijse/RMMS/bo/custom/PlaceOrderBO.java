package lk.ijse.RMMS.bo.custom;

import lk.ijse.RMMS.DTO.CustomerDTO;
import lk.ijse.RMMS.DTO.ItemDTO;
import lk.ijse.RMMS.DTO.OrderDetailDTO;
import lk.ijse.RMMS.bo.SuperBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    boolean placeOrder(String order_id, LocalDate dueDte, String cu_id, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException ;

    ItemDTO findItem(String item_code);

    CustomerDTO searchCustomer(String cu_id) throws SQLException, ClassNotFoundException;

    ItemDTO searchItem (String item_code) throws SQLException, ClassNotFoundException;

    boolean existCustomer(String cu_id) throws SQLException, ClassNotFoundException;

    boolean existItem (String item_code) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException ;

    String generateOrderID()throws SQLException, ClassNotFoundException;

    void JRDesignQuery();

}
