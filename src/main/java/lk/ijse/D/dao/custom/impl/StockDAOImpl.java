package lk.ijse.D.dao.custom.impl;

import lk.ijse.D.dao.SQLUtil;
import lk.ijse.D.dao.custom.StockDAO;
import lk.ijse.D.entity.Stock;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {

    @Override
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Stock");
        ArrayList<Stock> allStock = new ArrayList<>();

        while (rst.next()){
            Stock entity = new Stock(
                    rst.getString("id"),
                    rst.getString("material_name"),
                    rst.getInt("qty"),
                    rst.getString("description"),
                    rst.getDouble("unit_price")

            );
            allStock.add(entity);
        }
        return allStock;
    }

    @Override
    public boolean save(Stock entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Stock (id,material_name,qty,description,unit_price) VALUES (?,?,?,?,?)",entity.getId(),entity.getMaterial_name(),entity.getQty(),entity.getDescription(),entity.getUnit_price());


    }

    @Override
    public boolean update(Stock entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Stock SET material_name=?,qty=?,description=?,unit_price=? WHERE id=?",entity.getMaterial_name(),entity.getQty(),entity.getDescription(),entity.getUnit_price(),entity.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT id FROM Stock WHERE id =?",id);
        return rst.next();
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException{
        SQLUtil.execute("DELETE FROM Stock WHERE id=?",id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT id FROM Stock ORDER BY id DESC LIMIT 1;");
        if (rst.next()){
            String id = rst.getString("id");
            int newStockId = Integer.parseInt(id.replace("D00-",""))+1;
            return String.format("D00-%03d",newStockId);
        }else {
            return "D00-001";
        }
    }

    @Override
    public Stock search(String id) throws SQLException, ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT * FROM Stock WHERE id=?",id);
        rst.next();
        return new Stock(id+"",rst.getString("material_name"),rst.getInt("qty"),rst.getString("description"),rst.getDouble("unit_price"));
    }

    @Override
    public void JRDesignQuery() throws SQLException, ClassNotFoundException {
        SQLUtil.execute("SELECT * FROM stock");
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
       // jrDesignQuery.setText("SELECT * FROM stock ");
        JasperDesign load = null;
        load.setQuery(jrDesignQuery);
    }
}
