package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.ItemBO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DAO.ItemDAO;
import lk.ijse.gdse.DTO.ItemDTO;
import lk.ijse.gdse.DTO.OrderDetailsDTO;
import lk.ijse.gdse.Entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public boolean saveItem(ItemDTO item) throws SQLException {
        return itemDAO.save(new Item(item.getItemId(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand(), item.getStockId()));
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException {
        ArrayList<Item> all = itemDAO.getAll();
        List<ItemDTO> allItem = new ArrayList<>();
        for (Item item : all){
            allItem.add(new ItemDTO(item.getItemId(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand(),item.getStockId()));
        }
        return (ArrayList<ItemDTO>) allItem;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
        return itemDAO.update(new Item(itemDTO.getItemId(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand(), itemDTO.getStockId()));
    }

    @Override
    public List<String> getCodes() throws SQLException {
        return itemDAO.getTel();
    }

    @Override
    public Item searchById(String id) throws SQLException {
        return itemDAO.searchByTel(id);
    }

    @Override
    public boolean update1(List<OrderDetailsDTO> odList) throws SQLException {
        for (OrderDetailsDTO od : odList) {
            boolean isUpdateQty = updateQty(od.getItemId(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty(String Id, int qty) throws SQLException {
        return itemDAO.updateQty(Id,qty);
    }

    @Override
    public String getCurrentId() throws SQLException {
        return itemDAO.getCurrentId();
    }
}
