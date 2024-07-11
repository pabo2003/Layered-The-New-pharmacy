package lk.ijse.gdse.DAO;
import lk.ijse.gdse.Entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetails>{
    public boolean saveOrderDetails(List<OrderDetails> odList) throws SQLException;
}
