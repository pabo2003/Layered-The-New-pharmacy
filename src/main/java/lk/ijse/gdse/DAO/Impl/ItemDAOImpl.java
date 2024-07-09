package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.ItemDAO;
import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.Entity.Item;
import lk.ijse.gdse.Entity.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    public boolean save(Item item) throws SQLException {
        /*String sql = "INSERT INTO item VALUES(?,?,?,?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, item.getItemId());
        pstm.setObject(2, item.getDescription());
        pstm.setObject(3, item.getUnitPrice());
        pstm.setObject(4, item.getQtyOnHand());
        pstm.setObject(5, item.getStockId());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO item VALUES(?,?,?,?,?)",item.getItemId(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand(),item.getStockId());
    }


    public ArrayList<Item> getAll() throws SQLException {
        /*String sql = "SELECT*FROM item";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Item> itemList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String description = resultSet.getString(2);
            double unitePrice = Double.parseDouble(resultSet.getString(3));
            int qtyOnHand = Integer.parseInt(resultSet.getString(4));
            String stockId = resultSet.getString(5);

            Item item = new Item(id, description, unitePrice,qtyOnHand, stockId);
            itemList.add(item);
        }
        return itemList;*/
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT*FROM item");
        while (rst.next()){
            Item item = new Item(
                    rst.getString(1),
                    rst.getString(2),
                    Double.parseDouble(rst.getString(3)),
                    Integer.parseInt(rst.getString(4)),
                    rst.getString(5)
            );
            items.add(item);
        }
        return items;
    }


    public boolean delete(String id) throws SQLException {
        /*String sql = "DELETE FROM item WHERE itemId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("DELETE FROM item WHERE itemId = ?",id);
    }

    public boolean update(Item item) throws SQLException {
        /*String sql = "UPDATE item SET description = ? , qtyOnHand = ? , unitPrice = ? , stockId = ? WHERE itemId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,item.getDescription());
        pstm.setObject(2,item.getQtyOnHand());
        pstm.setObject(3,item.getUnitPrice());
        pstm.setObject(4,item.getStockId());
        pstm.setObject(5,item.getItemId());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("UPDATE item SET description = ? , qtyOnHand = ? , unitPrice = ? , stockId = ? WHERE itemId = ?",item.getDescription(),item.getQtyOnHand(),item.getUnitPrice(),item.getStockId(),item.getItemId());
    }

    public List<String> getTel() throws SQLException {
        /*String sql = "SELECT itemId FROM item";
        ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();

        ArrayList<String> idList = new ArrayList<>();
        while (resultSet.next()){
            String id = resultSet.getString(1);
            idList.add(id);

        }
        return idList;*/
        List<String> idList = new ArrayList<>();

        try (ResultSet rst = SQLUtil.execute("SELECT itemId FROM item")) {
            while (rst.next()) {
                String id = rst.getString(1);
                idList.add(id);
            }
        }

        return idList;
    }

    public Item searchByTel(String id) throws SQLException {
        /*String sql = "SELECT * FROM item WHERE itemId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String itemId = rs.getString(1);
            String description = rs.getString(2);
            double unitPrice = rs.getDouble(3);
            int qtyOnHand = rs.getInt(4);
            String stockId = rs.getString(5);

            Item item = new Item(itemId, description, unitPrice, qtyOnHand, stockId);
            return item;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE itemId = ?",id +"");
        rst.next();
        return new Item(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4),rst.getString(5));
    }


    public boolean update1(List<OrderDetails> odList) throws SQLException {
        for (OrderDetails od : odList) {
            boolean isUpdateQty = updateQty(od.getItemId(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(String Id, int qty) throws SQLException {
        String sql = "UPDATE item SET qtyOnHand = qtyOnHand - ? WHERE itemId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, Id);

        return pstm.executeUpdate() > 0;
    }

    public String getCurrentId() throws SQLException {
        /*String sql = "SELECT itemId FROM item ORDER BY itemId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String employeeId = resultSet.getString(1);
            return employeeId;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT itemId FROM item ORDER BY itemId DESC LIMIT 1");
        if(rst.next()) {
            String itemId = rst.getString(1);
            return itemId;
        }
        return null;
    }
}
