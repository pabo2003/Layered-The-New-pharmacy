package lk.ijse.gdse.DAO;

import lk.ijse.gdse.DTO.OrderDetailsDTO;
import lk.ijse.gdse.Entity.Item;

import java.util.List;

public interface ItemDAO extends CrudDAO<Item>{
    boolean update1(List<OrderDetailsDTO> odList);

    boolean updateQty(String id, int qty);
}
