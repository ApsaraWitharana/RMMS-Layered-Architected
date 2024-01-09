package lk.ijse.D.bo.custom.impl;

import lk.ijse.D.DTO.ItemDTO;
import lk.ijse.D.bo.custom.ItemBO;
import lk.ijse.D.dao.DAOFactory;
import lk.ijse.D.dao.custom.ItemDAO;
import lk.ijse.D.dao.custom.impl.ItemDAOImpl;
import lk.ijse.D.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item.getItem_code(), item.getName(), item.getDescription(), item.getUnit_price(), item.getQty()));

        }
        return itemDTOS;
    }

    @Override

    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItem_code(), dto.getName(), dto.getDescription(), dto.getUnit_price(), dto.getQty()));

    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getItem_code(), dto.getName(), dto.getDescription(), dto.getUnit_price(), dto.getQty()));
    }

    @Override
    public boolean existItem(String item_code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(item_code);
    }

    @Override
    public void deleteItem(String item_code) throws SQLException, ClassNotFoundException {
        itemDAO.delete(item_code);
    }

    @Override
    public String generateItemID() throws SQLException, ClassNotFoundException {
        return itemDAO.generateID();
    }

    @Override
    public ItemDTO searchItem(String item_code) throws SQLException, ClassNotFoundException {
        Item item = (Item) itemDAO.search(item_code);
        return new ItemDTO(item.getItem_code(), item.getName(), item.getDescription(), item.getUnit_price(), item.getQty());
    }

    @Override
    public void JRDesignQuery() {

    }

}

