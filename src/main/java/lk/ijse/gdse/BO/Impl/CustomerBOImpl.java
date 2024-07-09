package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.CustomerBO;
import lk.ijse.gdse.DAO.CustomerDAO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DTO.CustomerDTO;
import lk.ijse.gdse.Entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    public boolean saveCustomer(CustomerDTO customer) throws SQLException {
        return customerDAO.save(new Customer(customer.getCuId(), customer.getName(), customer.getNicNo(),customer.getAddress(), customer.getTel()));
    }

    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getCuId(),c.getName(),c.getNicNo(),c.getAddress(),c.getTel()));
        }
        return allCustomers;
    }

    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        return customerDAO.update(new Customer(customer.getCuId(),customer.getName(),customer.getNicNo(), customer.getAddress(), customer.getTel()));
    }

    public Customer searchByTel(String tel) throws SQLException {
        return (Customer) customerDAO.searchByTel(tel);
    }

    public List<String> getTel() throws SQLException {
        return customerDAO.getTel();
    }

    public String getCurrentId() throws SQLException {
        return customerDAO.getCurrentId();
    }
}
