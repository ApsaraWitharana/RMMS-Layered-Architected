package lk.ijse.D.dao.custom;

import lk.ijse.D.dao.CrudDAO;
//import net.sf.jasperreports.components.items.Item;
import lk.ijse.D.entity.Item;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item> {

    public  ArrayList<Item> getData() throws SQLException, ClassNotFoundException;
//    boolean save(lk.ijse.D.entity.Item entity) throws SQLException, ClassNotFoundException;
//
//    boolean update(lk.ijse.D.entity.Item entity) throws SQLException, ClassNotFoundException;
//

//    boolean save (lk.ijse.D.entity.Item entity) throws SQLException, ClassNotFoundException;
//
//    boolean update (lk.ijse.D.entity.Item entity) throws SQLException, ClassNotFoundException;


//    ArrayList<Item> getAll() throws SQLException, ClassNotFoundException ;
//
//    boolean save (Item entity) throws SQLException, ClassNotFoundException ;
//
//    boolean update (Item entity) throws SQLException, ClassNotFoundException ;
//
//    boolean exist (String item_code) throws SQLException, ClassNotFoundException;
//
//    void delete (String item_code) throws SQLException, ClassNotFoundException;
//
//    String generateID() throws SQLException, ClassNotFoundException;
//
//    Item search (String item_code) throws SQLException, ClassNotFoundException;


}
