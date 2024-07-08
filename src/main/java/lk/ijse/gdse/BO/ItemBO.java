package lk.ijse.gdse.BO;

import lk.ijse.gdse.DTO.ItemDTO;
import lk.ijse.gdse.DTO.OrderDetailsDTO;
import lk.ijse.gdse.Entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    public boolean saveItem(ItemDTO item) throws SQLException;


    public List<ItemDTO> getAllItem() throws SQLException;


    public boolean deleteItem(String id) throws SQLException;

    public boolean updateItem(Item item) throws SQLException;

    public List<String> getCodes() throws SQLException;

    public Item searchById(String id) throws SQLException;


    public boolean update1(List<OrderDetailsDTO> odList) throws SQLException;

    public boolean updateQty(String Id, int qty) throws SQLException;

    public String getCurrentId() throws SQLException;
}
