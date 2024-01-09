package lk.ijse.RMMS.dao.custom.impl;

import lk.ijse.RMMS.dao.SQLUtil;
import lk.ijse.RMMS.dao.custom.ItemDAO;
import lk.ijse.RMMS.entity.Item;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {


    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
        while (rst.next()) {
            allItems.add(new Item(
                    rst.getString("item_code"),
                    rst.getString("name"),
                    rst.getString("description"),
                    rst.getDouble("unit_price"),
                    rst.getInt("qty")
            ));

        }
        return allItems;


    }


    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Item (item_code,name,description,unit_price,qty) VALUES (?,?,?,?,?)",
                entity.getItem_code(), entity.getName(), entity.getDescription(), entity.getUnit_price(), entity.getQty());
    }


    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET name=?,description=?,unit_price=?,qty=? WHERE item_code=?",
                entity.getName(), entity.getDescription(), entity.getUnit_price(), entity.getQty() ,entity.getItem_code());
    }

    @Override
    public boolean exist(String item_code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT item_code FROM Item WHERE item_code=?", item_code);
        return rst.next();
    }


    @Override
    public void delete(String item_code) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM Item WHERE item_code=?", item_code);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT item_code FROM Item ORDER BY item_code DESC LIMIT 1;");
        if (rst.next()) {
            String item_code = rst.getString("item_code");
            int newItemCode = Integer.parseInt(item_code.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemCode);
        } else {
            return "I00-001";
        }
    }

    @Override

    public Item search(String item_code) throws SQLException, ClassNotFoundException {


        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE item_code=?", item_code);
        rst.next();
        return new Item (item_code, rst.getString("name"), rst.getString("description"), rst.getDouble("unit_price"), rst.getInt("qty"));
    }

    public  ArrayList<Item> getData() throws SQLException, ClassNotFoundException {

       // ObservableList<XYChart.Series<String, Integer>> datalist = FXCollections.observableArrayList();
        ArrayList<Item> allItems = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT name,qty FROM item ");

        while (rst.next()) {
            allItems.add(new Item(
                    rst.getString("name"),
                    rst.getInt("qty")
            ));

        }
        return allItems;

//        while(resultSet.next()){
//            String itemName = resultSet.getString("item_name");
//            int itemQty = resultSet.getInt("item_qty");
//            series.getData().add(new XYChart.Data<>(itemName, itemQty));
//        }
//
//        datalist.add(series);
//        return datalist;
    }

    @Override
    public void JRDesignQuery() throws SQLException, ClassNotFoundException {
        SQLUtil.execute("SELECT * FROM item");
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
      //  jrDesignQuery.setText("SELECT * FROM item ");
        JasperDesign load = null;
        load.setQuery(jrDesignQuery);
    }


}
