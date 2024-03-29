package lk.ijse.RMMS.bo.custom;

import lk.ijse.RMMS.dto.ItemDTO;
import lk.ijse.RMMS.dto.SupplierDTO;
import lk.ijse.RMMS.dto.SupplierOrderDetailDTO;
import lk.ijse.RMMS.bo.SuperBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceSupplierOrderBO extends SuperBO {

    boolean placeOrder(String order_id, LocalDate date, String su_id, List<SupplierOrderDetailDTO> supplierOrderDetails) throws SQLException, ClassNotFoundException ;

    boolean existItem (String item_code) throws SQLException, ClassNotFoundException;

    boolean existSupplier (String su_id) throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException ;

    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException ;

    ItemDTO searchItem (String item_code) throws SQLException, ClassNotFoundException;

    SupplierDTO searchSupplier (String su_id) throws SQLException, ClassNotFoundException;

    ItemDTO findItem(String item_code) ;

    //String generateOrderID()throws SQLException, ClassNotFoundException;

    String generateOrderId()throws SQLException, ClassNotFoundException;

    void   JRDesignQuery();
}
