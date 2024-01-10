package lk.ijse.RMMS.bo.custom.impl;

import lk.ijse.RMMS.dto.EmployeeDTO;
import lk.ijse.RMMS.bo.custom.EmployeeBO;
import lk.ijse.RMMS.dao.DAOFactory;
import lk.ijse.RMMS.dao.custom.EmployeeDAO;
import lk.ijse.RMMS.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees){
            employeeDTOS.add(new EmployeeDTO(employee.getEmp_id(),employee.getEmp_name(),employee.getEmp_dob(),employee.getEmp_address(),employee.getJobTitle(),employee.getEmp_nic(),employee.getContact(),employee.getEmp_email(),employee.getSalary()));
        }

        return employeeDTOS;
    }

    @Override
    public boolean saveEmployee (EmployeeDTO dto) throws SQLException, ClassNotFoundException {
       return employeeDAO.save(new Employee(dto.getEmp_id(),dto.getName(),dto.getDob(),dto.getAddress(),dto.getJobTitle(),dto.getNic(),dto.getContact(),dto.getEmail(),dto.getSalary()));
    }

    @Override
    public boolean updateEmployee (EmployeeDTO dto) throws SQLException, ClassNotFoundException {
         return employeeDAO.update(new Employee(dto.getEmp_id(),dto.getName(),dto.getDob(),dto.getAddress(),dto.getAddress(),dto.getJobTitle(),dto.getNic(),dto.getContact(),dto.getEmail()));
    }

    @Override
   public boolean existEmployee (String emp_id) throws SQLException, ClassNotFoundException{
      return employeeDAO.exist(emp_id);
    }

    @Override
  public   void deleteEmployee (String emp_id) throws SQLException, ClassNotFoundException{
           employeeDAO.delete(emp_id);
    }

    @Override
  public   String generateEmployeeID() throws SQLException, ClassNotFoundException{
         return employeeDAO.generateID();
    }

    @Override
   public EmployeeDTO searchEmployee (String emp_id) throws SQLException, ClassNotFoundException{
         Employee employee = employeeDAO.search(emp_id);
         EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmp_id(),employee.getEmp_name(),employee.getEmp_dob(),employee.getEmp_address(),employee.getJobTitle(),employee.getEmp_nic(),employee.getContact(),employee.getEmp_email(),employee.getSalary());
         return employeeDTO;
    }

    @Override
   public void   RDesignQuery() throws SQLException, ClassNotFoundException{

   }

}
