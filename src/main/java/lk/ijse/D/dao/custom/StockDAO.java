package lk.ijse.D.dao.custom;

import lk.ijse.D.dao.CrudDAO;
import lk.ijse.D.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;

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
