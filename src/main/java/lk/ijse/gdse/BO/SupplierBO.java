package lk.ijse.gdse.BO;

import lk.ijse.gdse.BO.SuperBO;
import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.DTO.SupplierDTO;
import lk.ijse.gdse.Entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {
    public boolean saveSupplier(Supplier supplier) throws SQLException;

    public List<SupplierDTO> getAllSupplier() throws SQLException;

    public boolean deleteSupplier(String id) throws SQLException;

    public boolean updateSupplier(Supplier supplier) throws SQLException;

    public Supplier searchById(String id) throws SQLException;

    public List<String> getId() throws SQLException;

    public String getCurrentId() throws SQLException;
}
