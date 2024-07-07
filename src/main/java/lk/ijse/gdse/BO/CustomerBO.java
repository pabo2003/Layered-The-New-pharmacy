package lk.ijse.gdse.BO;

import lk.ijse.gdse.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO{
    public boolean saveCustomer(Customer customer) throws SQLException;

    public List<Customer> getAllCustomer() throws SQLException;

    public boolean deleteCustomer(String id) throws SQLException;

    public boolean updateCustomer(Customer customer) throws SQLException;

    public Customer searchByTel(String tel) throws SQLException;

    public List<String> getTel() throws SQLException;

    public String getCurrentId() throws SQLException;
}
