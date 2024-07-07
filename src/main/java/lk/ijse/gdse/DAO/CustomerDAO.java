package lk.ijse.gdse.DAO;

import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO extends CrudDAO<CustomerDAO>{
    boolean save(Customer customer) throws SQLException;
}
