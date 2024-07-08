package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.CustomerDAO;
import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.Entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws SQLException {
        /*String sql = "INSERT INTO customer VALUES(?,?,?,?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, customer.getCuId());
        pstm.setObject(2, customer.getName());
        pstm.setObject(3, customer.getNicNo());
        pstm.setObject(4, customer.getAddress());
        pstm.setObject(5, customer.getTel());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO customer VALUES(?,?,?,?,?)",customer.getCuId(),customer.getName(),customer.getNicNo(),customer.getAddress(),customer.getTel());
    }


    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        /*String sql = "SELECT*FROM customer";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nicNo = resultSet.getString(3);
            String address = resultSet.getString(4);
            String tel = resultSet.getString(5);

            Customer customer = new Customer(id, name, nicNo, address, tel);
            cusList.add(customer);
        }
        return cusList;*/
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT*FROM customer");
        while (rst.next()){
            Customer customer = new Customer(
                    rst.getString("cuId"),
                    rst.getString("name"),
                    rst.getString("nicNo"),
                    rst.getString("address"),
                    rst.getString("tel")
            );
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        /*String sql = "DELETE FROM customer WHERE cuId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("DELETE FROM customer WHERE cuId = ?",id);
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        /*String sql = "UPDATE customer SET name = ? , nicNo = ? , address = ? , tel = ? WHERE cuId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,customer.getName());
        pstm.setObject(2,customer.getNicNo());
        pstm.setObject(3,customer.getAddress());
        pstm.setObject(4,customer.getTel());
        pstm.setObject(5,customer.getCuId());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("UPDATE customer SET name = ? , nicNo = ? , address = ? , tel = ? WHERE cuId = ?",customer.getName(),customer.getNicNo(),customer.getAddress(),customer.getTel(),customer.getCuId());
    }

    @Override
    public Customer searchByTel(String tel) throws SQLException {
        /*String sql = "SELECT * FROM customer WHERE tel = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, tel);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String cuId = resultSet.getString("cuId");
            String name = resultSet.getString("name");
            String nicNo = resultSet.getString("nicNo");
            String address = resultSet.getString("address");
            String tel1 = resultSet.getString("tel");

            Customer customer = new Customer(cuId, name, nicNo, address, tel1);

            return customer;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE tel = ?",tel +"");
        rst.next();
        return new Customer(rst.getString("cuId"),rst.getString("name"),rst.getString("nicNo"),rst.getString("address"),rst.getString("tel") );
    }

    @Override
    public List<String> getTel() throws SQLException {
        /*String sql = "SELECT tel from customer";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> cusList = new ArrayList<>();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            cusList.add(id);
        }
        return cusList;*/
        List<String> telList = new ArrayList<>();

        try (ResultSet rst = SQLUtil.execute("SELECT tel FROM customer")) {
            while (rst.next()) {
                String tel = rst.getString("tel");
                telList.add(tel);
            }
        }

        return telList;
    }

    @Override
    public String getCurrentId() throws SQLException {
        /*String sql = "SELECT cuId FROM customer ORDER BY cuId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String customerId = resultSet.getString(1);
            return customerId;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT cuId FROM customer ORDER BY cuId DESC LIMIT 1");
        if(rst.next()) {
            String customerId = rst.getString(1);
            return customerId;
        }
        return null;
    }
}
