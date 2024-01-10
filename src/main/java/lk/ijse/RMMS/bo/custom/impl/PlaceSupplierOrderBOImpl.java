package lk.ijse.RMMS.bo.custom.impl;

import lk.ijse.RMMS.dto.ItemDTO;
import lk.ijse.RMMS.dto.SupplierDTO;
import lk.ijse.RMMS.dto.SupplierOrderDetailDTO;
import lk.ijse.RMMS.bo.custom.PlaceSupplierOrderBO;
import lk.ijse.RMMS.dao.DAOFactory;
import lk.ijse.RMMS.dao.custom.ItemDAO;
import lk.ijse.RMMS.dao.custom.SupplierDAO;
import lk.ijse.RMMS.dao.custom.SupplierOrderDAO;
import lk.ijse.RMMS.dao.custom.SupplierOrderDetailDAO;
import lk.ijse.RMMS.dbConnection.DbConnection;
import lk.ijse.RMMS.entity.Item;
import lk.ijse.RMMS.entity.Supplier;
import lk.ijse.RMMS.entity.SupplierOrder;
import lk.ijse.RMMS.entity.SupplierOrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceSupplierOrderBOImpl implements PlaceSupplierOrderBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);


    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    SupplierOrderDAO supplierOrderDAO = (SupplierOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER);

    SupplierOrderDetailDAO supplierOrderDetailDAO = (SupplierOrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER_DETAIL);


    public boolean placeOrder(String order_id, LocalDate dueDte, String su_id, List<SupplierOrderDetailDTO> supplierOrderDetails) throws SQLException, ClassNotFoundException {

        Connection connection = null;
        connection = DbConnection.getDbConnection().getConnection();

        boolean b1 = supplierOrderDAO.exist(order_id);
        System.out.println("b1:"+b1);

        if (b1){
            return false;
        }

        connection.setAutoCommit(false);

        boolean b2 = supplierOrderDAO.save(new SupplierOrder(order_id,dueDte,su_id));
        System.out.println("b2:"+b2);
        if (!b2){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (SupplierOrderDetailDTO detail : supplierOrderDetails){
            boolean b3 = supplierOrderDetailDAO.save(new SupplierOrderDetail(detail.getOrder_id(),detail.getItem_code(),detail.getQty(),detail.getUnit_price()));
            System.out.println("b3:"+b3);
            if (!b3){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            System.out.println(detail.getItem_code());
            ItemDTO item = findItem(detail.getItem_code());
            item.setQty(item.getQty()-detail.getQty());

            boolean b = itemDAO.update(new Item(item.getItem_code(),item.getName(),item.getDescription(),item.getUnit_price(),item.getQty()));
            System.out.println("bb:"+b);
            if (!b){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    public ItemDTO findItem(String item_code) {
        try {
            Item item = itemDAO.search(item_code);
                return new ItemDTO(item.getItem_code(),item.getName(),item.getDescription(),item.getUnit_price(),item.getQty());


        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + item_code,e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public String generateOrderID() throws SQLException, ClassNotFoundException {
//        return null;
//    }

    @Override
    public String generateOrderId() throws SQLException, ClassNotFoundException {
        return  supplierOrderDAO.generateID();
    }

    @Override
    public void JRDesignQuery() {

    }

    @Override
    public SupplierDTO searchSupplier (String su_id) throws SQLException, ClassNotFoundException{

        Supplier supplier = supplierDAO.search(su_id);
        SupplierDTO supplierDTO = new SupplierDTO(supplier.getSu_id(),supplier.getSu_name(),supplier.getSu_address(),supplier.getContact(),supplier.getSu_email());
        return supplierDTO;

    }

    @Override
    public ItemDTO searchItem(String item_code) throws SQLException, ClassNotFoundException {
        Item item = (Item) itemDAO.search(item_code);
        return new ItemDTO(item.getItem_code(), item.getName(), item.getDescription(), item.getUnit_price(), item.getQty());
    }

    @Override
    public boolean existSupplier (String su_id) throws SQLException, ClassNotFoundException{
        return supplierDAO.exist(su_id);
    }

    @Override
    public boolean existItem(String item_code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(item_code);
    }

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
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item.getItem_code(), item.getName(), item.getDescription(), item.getUnit_price(), item.getQty()));

        }
        return itemDTOS;
    }

}
