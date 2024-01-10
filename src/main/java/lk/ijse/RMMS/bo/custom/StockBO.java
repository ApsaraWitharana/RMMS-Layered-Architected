package lk.ijse.RMMS.bo.custom;

import lk.ijse.RMMS.dto.StockDTO;
import lk.ijse.RMMS.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO extends SuperBO {

    ArrayList<StockDTO> getAllStock() throws SQLException, ClassNotFoundException ;

    boolean saveStock (StockDTO dto) throws SQLException, ClassNotFoundException ;

    boolean updateStock (StockDTO dto) throws SQLException, ClassNotFoundException ;

    boolean existStock (String id) throws SQLException, ClassNotFoundException;

    void deleteStock (String id) throws SQLException, ClassNotFoundException;

    String generateStockID() throws SQLException, ClassNotFoundException;

    StockDTO searchStock (String id) throws SQLException, ClassNotFoundException;

    void JRDesignQuery();
}
