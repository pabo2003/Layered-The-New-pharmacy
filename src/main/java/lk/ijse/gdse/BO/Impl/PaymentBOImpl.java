package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.PaymentBO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DAO.PaymentDAO;
import lk.ijse.gdse.DTO.PaymentDTO;
import lk.ijse.gdse.Entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public boolean savePayment(PaymentDTO payment) throws SQLException {
        return paymentDAO.save(new Payment(payment.getPayId(), payment.getMethod(), payment.getAmount(), payment.getDate()));
    }

    @Override
    public List<PaymentDTO> getAllPayment() throws SQLException {
        ArrayList<PaymentDTO> allPayments= new ArrayList<>();
        ArrayList<Payment> all = paymentDAO.getAll();
        for (Payment p : all) {
            allPayments.add(new PaymentDTO(p.getPayId(),p.getMethod(),p.getAmount(),p.getDate()));
        }
        return allPayments;
    }

    @Override
    public boolean deletePayment(String id) throws SQLException {
        return paymentDAO.delete(id);
    }

    @Override
    public boolean updatePayment(PaymentDTO payment) throws SQLException {
        return paymentDAO.update(new Payment(payment.getPayId(), payment.getMethod(), payment.getAmount(), payment.getDate()));
    }

    @Override
    public Payment searchById(String id) throws SQLException {
        return paymentDAO.searchByTel(id);
    }

    @Override
    public List<String> getId() throws SQLException {
        return paymentDAO.getTel();
    }

    @Override
    public String getCurrentId() throws SQLException {
        return paymentDAO.getCurrentId();
    }
}
