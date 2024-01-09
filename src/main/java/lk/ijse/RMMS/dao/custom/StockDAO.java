package lk.ijse.RMMS.dao.custom;

import lk.ijse.RMMS.dao.CrudDAO;
import lk.ijse.RMMS.entity.Stock;

import java.sql.SQLException;

public interface StockDAO extends CrudDAO<Stock> {

//    ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException ;
//
//    boolean save (Stock entity) throws SQLException, ClassNotFoundException ;
//
//    boolean update (Stock entity) throws SQLException, ClassNotFoundException ;
//
//    boolean exist (String id) throws SQLException, ClassNotFoundException;
//
//    void delete (String id) throws SQLException, ClassNotFoundException;
//
//    String generateID() throws SQLException, ClassNotFoundException;
//
//    Stock search (String id) throws SQLException, ClassNotFoundException;

    void JRDesignQuery() throws SQLException, ClassNotFoundException;
}
