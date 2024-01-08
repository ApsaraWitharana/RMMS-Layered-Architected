package lk.ijse.D.bo.custom.impl;

import lk.ijse.D.DTO.StockDTO;
import lk.ijse.D.bo.custom.StockBO;
import lk.ijse.D.dao.DAOFactory;
import lk.ijse.D.dao.custom.StockDAO;
import lk.ijse.D.dao.custom.impl.StockDAOImpl;
import lk.ijse.D.entity.Stock;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {

    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);

    @Override
    public ArrayList<StockDTO> getAllStock() throws SQLException, ClassNotFoundException {
        ArrayList<Stock> stocks = stockDAO.getAll();
        ArrayList<StockDTO> stockDTOS = new ArrayList<>();
        for (Stock stock:stocks){
            stockDTOS.add(new StockDTO(stock.getId(),stock.getMaterial_name(),stock.getQty(),stock.getDescription(),stock.getUnit_price()));
        }
        return stockDTOS;
    }

    @Override
   public boolean saveStock (StockDTO dto) throws SQLException, ClassNotFoundException {
        return stockDAO.save(new Stock(dto.getId(),dto.getMaterial_name(),dto.getQty(),dto.getDescription(),dto.getUnit_price()));
    }

    @Override
   public boolean updateStock (StockDTO dto) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(dto.getId(),dto.getMaterial_name(),dto.getQty(),dto.getDescription(),dto.getUnit_price()));

   }

   @Override
    public boolean existStock (String id) throws SQLException, ClassNotFoundException{
        return stockDAO.exist(id);
    }

    @Override
    public void deleteStock (String id) throws SQLException, ClassNotFoundException{
          stockDAO.delete(id);
    }

    @Override
    public String generateStockID() throws SQLException, ClassNotFoundException{
          return stockDAO.generateID();
    }

    @Override
    public StockDTO searchStock (String id) throws SQLException, ClassNotFoundException{
            Stock stock = stockDAO.search(id);
            StockDTO stockDTO = new StockDTO(stock.getId(),stock.getMaterial_name(),stock.getQty(),stock.getDescription(),stock.getUnit_price());
            return stockDTO;
    }

}
