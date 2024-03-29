package lk.ijse.RMMS.bo.custom.impl;

import lk.ijse.RMMS.dto.SupplierDTO;
import lk.ijse.RMMS.bo.custom.SupplierBO;
import lk.ijse.RMMS.dao.DAOFactory;
import lk.ijse.RMMS.dao.custom.SupplierDAO;
import lk.ijse.RMMS.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {


    SupplierDAO supplierDAO =  (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier supplier : suppliers){
            supplierDTOS.add(new SupplierDTO(supplier.getSu_id(),supplier.getSu_name(),supplier.getSu_address(),supplier.getContact(),supplier.getSu_email()));
        }
        return supplierDTOS;
    }

    @Override
   public boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {

        return supplierDAO.save(new Supplier(dto.getSu_id(),dto.getSu_name(),dto.getSu_address(),dto.getContact(),dto.getSu_email()));

    }

    @Override
  public   boolean updateSupplier (SupplierDTO dto) throws SQLException, ClassNotFoundException {

        return supplierDAO.update(new Supplier(dto.getSu_id(),dto.getSu_name(),dto.getSu_address(),dto.getContact(),dto.getSu_email()));


    }

    @Override
   public boolean existSupplier (String su_id) throws SQLException, ClassNotFoundException{
        return supplierDAO.exist(su_id);
    }

    @Override
   public void deleteSupplier (String su_id) throws SQLException, ClassNotFoundException{
        supplierDAO.delete(su_id);

    }

    @Override
   public String generateSupplierID() throws SQLException, ClassNotFoundException{

        return supplierDAO.generateID();

    }

    @Override
   public SupplierDTO searchSupplier (String su_id) throws SQLException, ClassNotFoundException{

        Supplier supplier = supplierDAO.search(su_id);
        SupplierDTO supplierDTO = new SupplierDTO(supplier.getSu_id(),supplier.getSu_name(),supplier.getSu_address(),supplier.getContact(),supplier.getSu_email());
        return supplierDTO;

    }

    @Override
    public void JRDesignQuery() {

    }
}


