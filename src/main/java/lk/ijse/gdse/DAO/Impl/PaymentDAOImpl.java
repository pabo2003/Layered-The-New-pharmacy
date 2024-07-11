package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.PaymentDAO;
import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.DTO.PaymentDTO;
import lk.ijse.gdse.Entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    public boolean save(Payment payment) throws SQLException {
        /*String sql = "INSERT INTO payment VALUES(?,?,?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, payment.getPayId());
        pstm.setObject(2, payment.getMethod());
        pstm.setObject(3, payment.getAmount());
        pstm.setObject(4, payment.getDate());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO payment VALUES(?,?,?,?)",
                payment.getPayId(),
                payment.getMethod(),
                payment.getAmount(),
                payment.getDate());
    }

    public ArrayList<Payment> getAll() throws SQLException {
        /*String sql = "SELECT * FROM payment";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Payment> paymentsList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String method = resultSet.getString(2);
            double amount = Double.parseDouble(resultSet.getString(3));
            Date date = resultSet.getDate("4");


            Payment payment = new Payment(id, method, amount, date);
            paymentsList.add(payment);
        }
        return paymentsList;*/
        ArrayList<Payment> payments = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");
        while (rst.next()){
            Payment payment = new Payment(rst.getString(1),rst.getString(3),rst.getDouble(3),rst.getDate(4));
            payments.add(payment);
        }
        return payments;
    }

    public boolean delete(String id) throws SQLException {
        /*String sql = "DELETE FROM payment WHERE payId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("DELETE FROM payment WHERE payId = ?",id);
    }

    public boolean update(Payment payment) throws SQLException {
        /*String sql = "UPDATE payment SET method = ? , amount = ? , date ? WHERE payId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,payment.getMethod());
        pstm.setObject(3,payment.getAmount());
        pstm.setObject(2,payment.getDate());
        pstm.setObject(4,payment.getPayId());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("UPDATE payment SET method = ? , amount = ? , date ? WHERE payId = ?",payment.getMethod(),payment.getAmount(),payment.getDate(),payment.getPayId());
    }

    public Payment searchByTel(String id) throws SQLException {
        /*String sql = "SELECT*FROM payment WHERE payId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String payId = resultSet.getString(1);
            String method = resultSet.getString(2);
            double amount = Double.parseDouble(resultSet.getString(3));
            Date date = resultSet.getDate(4);

            Payment payment = new Payment(payId, method, amount, date);

            return payment;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT*FROM payment WHERE payId = ?",id+"");
        rst.next();
        return new Payment(rst.getString(1), rst.getString(2), rst.getDouble(3),rst.getDate(4));
    }

    public List<String> getTel() throws SQLException {
        /*String sql = "SELECT payId from payment";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> paymentList = new ArrayList<>();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            paymentList.add(id);
        }
        return paymentList;*/
        List<String> idList = new ArrayList<>();
        try (ResultSet rst = SQLUtil.execute("SELECT payId from payment")) {
            while (rst.next()) {
                String id = rst.getString(1);
                idList.add(id);
            }
        }

        return idList;
    }
    public String getCurrentId() throws SQLException {
        /*String sql = "SELECT payId FROM payment ORDER BY payId DESC LIMIT 1";
        try (PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getString("payId");
            }
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT payId FROM payment ORDER BY payId DESC LIMIT 1");
        if (rst.next()) {
            String payId = rst.getString(1);
            return payId;
        } else {
            return null;
        }
    }
}
