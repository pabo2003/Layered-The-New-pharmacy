package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.DAO.SupplierDetailsDAO;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.Entity.Stock;
import lk.ijse.gdse.Entity.SupplierDetails;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDetailsDAOImpl implements SupplierDetailsDAO {
    public boolean saveSupplierDetails(List<SupplierDetails> odList) throws SQLException {
        for (SupplierDetails od : odList){
            boolean isSaved = saveSupplierDetails((List<SupplierDetails>) od);
            if (isSaved) {
                return false;
            }
        }
        return true;
    }

    public boolean save(SupplierDetails od) throws SQLException {
        /*String sql = "INSERT INTO supplierDetails (supplierId,stockId,date) VALUES (?,?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1,od.getSupplierId());
        pstm.setString(2,od.getStockId());
        pstm.setString(3, String.valueOf(od.getDate()));

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO supplierDetails (supplierId,stockId,date) VALUES (?,?,?)",od.getSupplierId(),od.getStockId(),od.getDate());
    }

    public ArrayList<SupplierDetails> getAll() throws SQLException {
        /*String sql = "SELECT *FROM supplierDetails";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<SupplierDetails> SDList = new ArrayList<>();

        while (resultSet.next()){
            String stockId = resultSet.getString(1);
            String supplierId = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));

            SupplierDetails supplierDetails = new SupplierDetails(stockId,supplierId,date);
            SDList.add(supplierDetails);
        }
        return SDList;*/
        ArrayList<SupplierDetails> supplierDetails = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT *FROM supplierDetails");
        while (rst.next()){
            SupplierDetails supplierDetail = new SupplierDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3)

            );
            supplierDetails.add(supplierDetail);
        }
        return supplierDetails;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(SupplierDetails entity) throws SQLException {
        return false;
    }

    @Override
    public SupplierDetails searchByTel(String tel) throws SQLException {
        return null;
    }

    @Override
    public List<String> getTel() throws SQLException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException {
        return null;
    }
}
