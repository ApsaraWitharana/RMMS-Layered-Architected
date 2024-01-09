package lk.ijse.RMMS.dao.custom.impl;

import lk.ijse.RMMS.dao.SQLUtil;
import lk.ijse.RMMS.dao.custom.CustomerDAO;
import lk.ijse.RMMS.entity.Customer;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> allCustomer = new ArrayList<>();

        while (rst.next()){

            Customer entity = new Customer(
                    rst.getString("cu_id"),
                    rst.getString("cu_name"),
                    rst.getString("address"),
                    rst.getString("cu_contact"),
                    rst.getString("cu_email")
            );
            allCustomer.add(entity);
        }
        return allCustomer;
    }

    @Override
    public boolean save (Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer (cu_id ,cu_name ,address , cu_contact,cu_email) VALUES (?,?,?,?,?)",entity.getCu_id(),entity.getCu_name(),entity.getAddress(),entity.getCu_contact(),entity.getCu_email());
    }

    @Override
    public boolean update (Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET cu_email =?,cu_contact=?,address =?,cu_name=? WHERE cu_id =?",
               entity.getCu_email(),entity.getCu_contact(),entity.getAddress(),entity.getCu_name(), entity.getCu_id());
    }

    @Override
    public boolean exist (String cu_id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT cu_id FROM Customer WHERE cu_id = ?",cu_id);
        return rst.next();
    }

    @Override
    public void delete (String cu_id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("DELETE FROM Customer WHERE cu_id = ?",cu_id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT cu_id FROM Customer ORDER BY cu_id DESC LIMIT 1 ;");
        if (rst.next()){
            String cu_id = rst.getString("cu_id");
            int newCustomerId = Integer.parseInt(cu_id.replace("C00-","")) + 1;
            return String.format("C00-%03d",newCustomerId);


        }else {
            return "C00-001";
        }
    }

    @Override
    public Customer search (String cu_id) throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("SELECT * FROM Customer WHERE cu_id =?",cu_id);
        rst.next();
        return new Customer(cu_id+"",rst.getString("cu_name"),rst.getString("address"),rst.getString("cu_contact"),rst.getString("cu_email"));

    }


    @Override
    public void JRDesignQuery() throws SQLException, ClassNotFoundException {
        SQLUtil.execute("SELECT * FROM customer");
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
       // jrDesignQuery.setText("SELECT * FROM customer ");
        JRDesignDataset load = null;
        load.setQuery(jrDesignQuery);
    }
}
