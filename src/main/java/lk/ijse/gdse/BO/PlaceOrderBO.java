package lk.ijse.gdse.BO;

import lk.ijse.gdse.DAO.Impl.ItemDAOImpl;
import lk.ijse.gdse.DAO.Impl.OrderDAOImpl;
import lk.ijse.gdse.DAO.Impl.OrderDetailDAOImpl;
import lk.ijse.gdse.DAO.Impl.PaymentDAOImpl;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.DTO.PlaceOrderDTO;
import lk.ijse.gdse.Entity.Order;
import lk.ijse.gdse.Entity.OrderDetails;
import lk.ijse.gdse.Entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO{
    public String getCurrentId() throws SQLException;
    public boolean saveOrder(Order order) throws SQLException;
    public boolean save(List<OrderDetails> odList) throws SQLException;
    public boolean saveOrderDetail(OrderDetails od) throws SQLException;
    public boolean placeOrder(PlaceOrder po) throws SQLException ;

}
