package lk.ijse.gdse.BO;

import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.DTO.SupplierDetailsDTO;
import lk.ijse.gdse.Entity.SupplierDetails;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierDetailsBO extends SuperBO{
    public boolean save(List<SupplierDetailsDTO> odList) throws SQLException;

    public boolean saveSupplierDetails(SupplierDetailsDTO od) throws SQLException;

    public List<SupplierDetailsDTO> getAllSuppliers() throws SQLException;
}
