package lk.ijse.RMMS.bo.custom;

import lk.ijse.RMMS.dto.ItemDTO;
import lk.ijse.RMMS.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {



    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException ;

    boolean saveItem (ItemDTO dto) throws SQLException, ClassNotFoundException ;

    boolean updateItem (ItemDTO dto) throws SQLException, ClassNotFoundException ;

    boolean existItem (String item_code) throws SQLException, ClassNotFoundException;

    void deleteItem (String item_code) throws SQLException, ClassNotFoundException;

    String generateItemID() throws SQLException, ClassNotFoundException;

    ItemDTO searchItem (String item_code) throws SQLException, ClassNotFoundException;

     void JRDesignQuery();
}
