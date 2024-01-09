package lk.ijse.RMMS.dao.custom.impl;

import lk.ijse.RMMS.dao.SQLUtil;
import lk.ijse.RMMS.dao.custom.EmployeeDAO;
import lk.ijse.RMMS.entity.Employee;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
@Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee");
        ArrayList<Employee> allEmployee = new ArrayList<>();

        while (rst.next()){
            Employee entity = new Employee(
                    rst.getString("emp_id"),
                    rst.getString("emp_name"),
                    rst.getString("emp_dob"),
                    rst.getString("emp_address"),
                    rst.getString("job_title"),
                    rst.getString("emp_nic"),
                    rst.getString("contact"),
                    rst.getString("emp_email"),
                   rst.getString("salary")

            );
            allEmployee.add(entity);


        }
        return allEmployee;
    }
@Override
   public boolean save (Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee (emp_id,emp_name,emp_dob,emp_address,job_title,emp_nic,contact,emp_email,salary) VALUES (?,?,?,?,?,?,?,?,?)",entity.getEmp_id(),entity.getEmp_name(),entity.getEmp_dob(),entity.getEmp_address(),entity.getJobTitle(),entity.getEmp_nic(),entity.getContact(),entity.getEmp_email(),entity.getSalary());


    }
@Override
   public boolean update (Employee entity) throws SQLException, ClassNotFoundException {
    return SQLUtil.execute("UPDATE Employee SET emp_name=?,emp_dob=?,emp_address=?,job_title=?,emp_nic=?,contact=?,emp_email=?,salary=? WHERE emp_id=?",
            entity.getEmp_name(),entity.getEmp_dob(),entity.getEmp_address(),entity.getJobTitle(),entity.getEmp_nic(),entity.getContact(),entity.getEmp_email(),entity.getSalary(),entity.getEmp_id());

    }

    public boolean exist (String emp_id) throws SQLException, ClassNotFoundException{
       ResultSet rst =SQLUtil.execute("SELECT emp_id FROM Employee WHERE emp_id=?",emp_id);
       return rst.next();
    }

    public void delete (String emp_id) throws SQLException, ClassNotFoundException{

    SQLUtil.execute("DELETE FROM Employee WHERE emp_id=?",emp_id);


    }
   public String generateID() throws SQLException, ClassNotFoundException{
    ResultSet rst = SQLUtil.execute("SELECT emp_id FROM Employee ORDER BY emp_id DESC LIMIT 1;");
    if (rst.next()){
        String emp_id = rst.getString("emp_id");
        int newEmployeeId = Integer.parseInt(emp_id.replace("E00-",""))+ 1;
        return String.format("E00-%03d",newEmployeeId);

    }else {
        return "E00-001";
    }

    }

   public Employee search (String emp_id) throws SQLException, ClassNotFoundException{
          ResultSet rst = SQLUtil.execute("SELECT * FROM Employee WHERE emp_id=?",emp_id);
          rst.next();
          return new Employee(emp_id+"",rst.getString("emp_name"),rst.getString("emp_dob"),rst.getString("emp_address"),rst.getString("job_title"),rst.getString("emp_nic"),rst.getString("contact"),rst.getString("emp_email"),rst.getString("salary"));

    }

    @Override
    public void RDesignQuery() throws SQLException, ClassNotFoundException {
        SQLUtil.execute("SELECT * FROM employee ") ;
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        //jrDesignQuery.setText("SELECT * FROM employee ");
        JasperDesign load = null;
        load.setQuery(jrDesignQuery);
    }
}
