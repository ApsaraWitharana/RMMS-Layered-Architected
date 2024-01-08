package lk.ijse.D.bo.custom;

import lk.ijse.D.DTO.StockDTO;
import lk.ijse.D.bo.SuperBO;
import lk.ijse.D.dao.SuperDAO;

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

}
