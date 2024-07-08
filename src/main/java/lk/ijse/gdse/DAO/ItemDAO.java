package lk.ijse.gdse.DAO;

import lk.ijse.gdse.Entity.Item;

import java.util.List;

public interface ItemDAO extends CrudDAO<Item>{
    List<String> getCodes();

    Item SearchById(String id);

    boolean updateQty(String id, int qty);
}
