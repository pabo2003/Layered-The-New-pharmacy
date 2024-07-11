package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.OrderDetailDAO;
import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.Entity.OrderDetails;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    public boolean saveOrderDetails(List<OrderDetails> odList) throws SQLException {
        for (OrderDetails od : odList) {
            boolean isSaved = saveOrderDetails((List<OrderDetails>) od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save(OrderDetails od) throws SQLException {
        /*String sql = "INSERT INTO orderDetails (itemId, orderId, qty, unitPrice) VALUES (?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, od.getItemId());
        pstm.setString(2, od.getOrderId());
        pstm.setInt(3, od.getQty());
        pstm.setDouble(4, od.getUnitPrice());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO orderDetails (itemId, orderId, qty, unitPrice) VALUES (?, ?, ?, ?)",od.getItemId(),od.getOrderId(),od.getQty(),od.getUnitPrice());
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException {
        return false;
    }

    @Override
    public OrderDetails searchByTel(String tel) throws SQLException {
        return null;
    }

    @Override
    public List<String> getTel() throws SQLException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException {
        return null;
    }
}
