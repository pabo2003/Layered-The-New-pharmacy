package lk.ijse.gdse.DAO;

import lk.ijse.gdse.Entity.Payment;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment>{
    Payment searchById(String id);

    List<String> getId();
}
