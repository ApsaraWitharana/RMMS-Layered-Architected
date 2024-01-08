package lk.ijse.D.bo.custom;

import lk.ijse.D.DTO.SupplierDTO;
import lk.ijse.D.bo.SuperBO;
import lk.ijse.D.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {

    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException ;

    boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    boolean updateSupplier (SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    boolean existSupplier (String su_id) throws SQLException, ClassNotFoundException;

    void deleteSupplier (String su_id) throws SQLException, ClassNotFoundException;

    String generateSupplierID() throws SQLException, ClassNotFoundException;

    SupplierDTO searchSupplier (String su_id) throws SQLException, ClassNotFoundException;
}
