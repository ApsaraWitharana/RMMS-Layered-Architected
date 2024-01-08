package lk.ijse.D.dao;

import lk.ijse.D.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO{

     ArrayList<T> getAll() throws SQLException, ClassNotFoundException ;

    boolean save (T dto) throws SQLException, ClassNotFoundException ;

    boolean update (T dto) throws SQLException, ClassNotFoundException ;

    boolean exist (String id) throws SQLException, ClassNotFoundException;

     void delete (String id) throws SQLException, ClassNotFoundException;

    String generateID() throws SQLException, ClassNotFoundException;

    T search (String id) throws SQLException, ClassNotFoundException;


}
