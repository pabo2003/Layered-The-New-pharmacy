package lk.ijse.gdse.BO;

import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.Entity.OrderDetails;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsBO extends SuperBO{
    public boolean save(List<OrderDetails> odList) throws SQLException;

    public boolean saveOrderDetail(OrderDetails od) throws SQLException;
}
