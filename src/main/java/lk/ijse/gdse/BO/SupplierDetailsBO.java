package lk.ijse.gdse.BO;

import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.DTO.SupplierDetailsDTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierDetailsBO extends SuperBO{
    public boolean save(List<SupplierDetails> odList) throws SQLException;

    public boolean saveSupplierDetails(SupplierDetails od) throws SQLException;

    public List<SupplierDetailsDTO> getAllSuppliers() throws SQLException;
}
