package lk.ijse.gdse.BO;

import lk.ijse.gdse.DTO.CustomerDTO;
import lk.ijse.gdse.Entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO{
    public  boolean saveCustomer(CustomerDTO customer) throws SQLException;

    public List<CustomerDTO> getAllCustomer() throws SQLException;

    public boolean deleteCustomer(String id) throws SQLException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;

    public Customer searchByTel(String tel) throws SQLException;

    public List<String> getTel() throws SQLException;

    public String getCurrentId() throws SQLException;
}
