package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.DAO.StockDAO;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.Entity.Customer;
import lk.ijse.gdse.Entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    public boolean save(Stock stock) throws SQLException {
        /*String sql = "INSERT INTO stock VALUES(?,?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, stock.getStockId());
        pstm.setObject(2, stock.getDescription());
        pstm.setObject(3, stock.getCategory());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO stock VALUES(?,?,?)",stock.getStockId(),stock.getDescription(),stock.getCategory());
    }

    public ArrayList<Stock> getAll() throws SQLException {
        /*String sql = "SELECT * FROM stock";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Stock> stocksList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String description = resultSet.getString(2);
            String category = resultSet.getString(3);


            Stock stock = new Stock(id,description,category);
            stocksList.add(stock);
        }
        return stocksList;*/
        ArrayList<Stock> stocks = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM stock");
        while (rst.next()){
            Stock stock = new Stock(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)

            );
            stocks.add(stock);
        }
        return stocks;
    }

    public boolean delete(String id) throws SQLException {
        /*String sql = "DELETE FROM stock WHERE stockId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("DELETE FROM stock WHERE stockId = ?",id);
    }

    public boolean update(Stock stock) throws SQLException {
        /*String sql = "UPDATE stock SET description = ? , category = ? WHERE stockId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,stock.getDescription());
        pstm.setObject(2,stock.getCategory());
        pstm.setObject(3,stock.getStockId());


        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("UPDATE stock SET description = ? , category = ? WHERE stockId = ?",stock.getDescription(),stock.getCategory(),stock.getStockId());

    }

    public Stock searchByTel(String id) throws SQLException {
        /*String sql = "SELECT*FROM stock WHERE stockId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String stockId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String category = resultSet.getString(3);

            Stock stock = new Stock(stockId, description, category);

            return stock;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT*FROM stock WHERE stockId = ?",id +"");
        if (rst.next()) {
            return new Stock(rst.getString(1), rst.getString(2), rst.getString(3));
        }
        return null;
    }

    public List<String> getTel() throws SQLException {
        /*String sql = "SELECT stockId from stock";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> stockList = new ArrayList<>();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            stockList.add(id);
        }
        return stockList;*/
        List<String> idList = new ArrayList<>();

        try (ResultSet rst = SQLUtil.execute("SELECT stockId from stock")) {
            while (rst.next()) {
                String id = rst.getString(1);
                idList.add(id);
            }
        }

        return idList;
    }

    public String getCurrentId() throws SQLException {
        /*String sql = "SELECT stockId FROM stock ORDER BY stockId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String employeeId = resultSet.getString(1);
            return employeeId;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT stockId FROM stock ORDER BY stockId DESC LIMIT 1");
        if(rst.next()) {
            String id = rst.getString(1);
            return id;
        }
        return null;
    }
}
