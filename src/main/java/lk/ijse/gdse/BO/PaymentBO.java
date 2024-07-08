package lk.ijse.gdse.BO;

import lk.ijse.gdse.DTO.PaymentDTO;
import lk.ijse.gdse.Entity.Payment;

import java.sql.*;
import java.util.List;

public interface PaymentBO extends SuperBO{
    public boolean savePayment(PaymentDTO payment) throws SQLException;

    public List<PaymentDTO> getAllPayment() throws SQLException;

    public boolean deletePayment(String id) throws SQLException;

    public boolean updatePayment(PaymentDTO payment) throws SQLException;

    public Payment searchById(String id) throws SQLException;

    public List<String> getId() throws SQLException;
    public String getCurrentId() throws SQLException;
}
