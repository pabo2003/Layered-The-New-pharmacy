
package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.DTO.OrderDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl {
    public static String getCurrentId() throws SQLException {
        /*String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if(rst.next()) {
            String orderId = rst.getString(1);
            return orderId;
        }
        return null;
    }

    public static boolean save(OrderDTO order) throws SQLException {
        /*String sql = "INSERT INTO orders VALUES(?, ?, ?,?,?,?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
            pstm.setString(1, order.getOrderId());
            pstm.setString(2, order.getDescription());
            pstm.setString(3, String.valueOf(order.getPaymentAmount()));
            pstm.setDate(4, order.getDate());
            pstm.setString(5, order.getCuId());
            pstm.setString(6, order.getPayId());
            pstm.setString(7, order.getEmployeeId());
        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?,?,?,?,?)",order.getOrderId(),order.getDescription(),order.getPaymentAmount(),order.getDate(),order.getCuId(),order.getPayId(),order.getEmployeeId());
    }


}

