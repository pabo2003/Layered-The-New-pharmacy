package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.SupplierDetailsBO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DAO.StockDAO;
import lk.ijse.gdse.DAO.SupplierDetailsDAO;
import lk.ijse.gdse.DTO.SupplierDTO;
import lk.ijse.gdse.DTO.SupplierDetailsDTO;
import lk.ijse.gdse.Entity.SupplierDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDetailsBOImpl implements SupplierDetailsBO {
    SupplierDetailsDAO supplierDetailsDAO = (SupplierDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER_DETAILS);
    @Override
    public boolean save(List<SupplierDetails> odList) throws SQLException {
        return false;
    }

    @Override
    public boolean saveSupplierDetails(SupplierDetails od) throws SQLException {
        return supplierDetailsDAO.save(new SupplierDetails(od.getSupplierId(), od.getStockId(), od.getDate()));
    }

    @Override
    public ArrayList<SupplierDetailsDTO> getAllSuppliers() throws SQLException {
        ArrayList<SupplierDetailsDTO> allSupplierDetails= new ArrayList<>();
        ArrayList<SupplierDetails> all = supplierDetailsDAO.getAll();
        for (SupplierDetails sd : all) {
            allSupplierDetails.add(new SupplierDetailsDTO(sd.getSupplierId(),sd.getStockId(),sd.getDate()));
        }
        return allSupplierDetails;
    }
}
