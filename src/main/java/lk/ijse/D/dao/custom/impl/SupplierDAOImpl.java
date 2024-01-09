package lk.ijse.D.dao.custom.impl;

import lk.ijse.D.dao.SQLUtil;
import lk.ijse.D.dao.custom.SupplierDAO;
import lk.ijse.D.entity.Item;
import lk.ijse.D.entity.Supplier;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier") ;
       ArrayList<Supplier> allSupplier = new ArrayList<>();

       while (rst.next()){
           Supplier entity = new Supplier(
                   rst.getString("su_id"),
                   rst.getString("su_name"),
                   rst.getString("su_address"),
                   rst.getString("contact"),
                   rst.getString("su_email")
           );

           allSupplier.add(entity);
       }
       return allSupplier;
    }

   public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO Supplier (su_id,su_name,su_address,contact,su_email) VALUES (?,?,?,?,?)",entity.getSu_id(),entity.getSu_name(),entity.getSu_address(),entity.getContact(),entity.getSu_email());
   }

    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE Supplier SET su_name=?, su_address=?,contact=?,su_email=? WHERE su_id=?",entity.getSu_name(),entity.getSu_address(),entity.getContact(),entity.getSu_email(),entity.getSu_id());

    }

    public boolean exist(String su_id) throws SQLException, ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT su_id FROM Supplier WHERE su_id=?",su_id);
        return rst.next();
    }

    public void delete(String su_id) throws SQLException, ClassNotFoundException{
        SQLUtil.execute("DELETE FROM Supplier WHERE su_id=?",su_id);

    }

    public String generateID() throws SQLException, ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT su_id FROM Supplier ORDER BY su_id DESC LIMIT 1;");
        if (rst.next()){
            String su_id = rst.getString("su_id");
            int newSupplierId = Integer.parseInt(su_id.replace("S00-","")) + 1;
            return String.format("S00-%03d",newSupplierId);

        }else {
            return "S00-001";
        }

    }

    public Supplier search(String su_id) throws SQLException, ClassNotFoundException{
        ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier WHERE su_id=?",su_id);
        rst.next();
        return  new Supplier(su_id+"",rst.getString("su_name"),rst.getString("su_address"),rst.getString("contact"),rst.getString("su_email"));

    }

    @Override
    public void JRDesignQuery() throws SQLException, ClassNotFoundException {
        SQLUtil.execute("SELECT * FROM supplier ");
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        //jrDesignQuery.setText("SELECT * FROM supplier ");
        JasperDesign load = null;
        load.setQuery(jrDesignQuery);
    }
}
