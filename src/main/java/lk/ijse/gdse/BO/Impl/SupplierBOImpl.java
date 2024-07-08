package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.SupplierBO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DAO.StockDAO;
import lk.ijse.gdse.DAO.SupplierDAO;
import lk.ijse.gdse.DTO.CustomerDTO;
import lk.ijse.gdse.DTO.SupplierDTO;
import lk.ijse.gdse.Entity.Customer;
import lk.ijse.gdse.Entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public boolean saveSupplier(Supplier supplier) throws SQLException {
        return supplierDAO.save(new Supplier(supplier.getSupplierId(), supplier.getName(), supplier.getDescription(), supplier.getAddress(), supplier.getTel()));
    }

    @Override
    public List<SupplierDTO> getAllSupplier() throws SQLException {
        ArrayList<SupplierDTO> allSuppliers= new ArrayList<>();
        ArrayList<Supplier> all = supplierDAO.getAll();
        for (Supplier s : all) {
            allSuppliers.add(new SupplierDTO(s.getSupplierId(),s.getName(),s.getDescription(),s.getAddress(),s.getTel()));
        }
        return allSuppliers;
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) throws SQLException {
        return supplierDAO.update(new Supplier(supplier.getSupplierId(), supplier.getName(), supplier.getDescription(), supplier.getAddress(), supplier.getTel()));
    }

    @Override
    public Supplier searchById(String id) throws SQLException {
        return supplierDAO.searchByTel(id);
    }

    @Override
    public List<String> getId() throws SQLException {
        return supplierDAO.getTel();
    }

    @Override
    public String getCurrentId() throws SQLException {
        return supplierDAO.getCurrentId();
    }
}
