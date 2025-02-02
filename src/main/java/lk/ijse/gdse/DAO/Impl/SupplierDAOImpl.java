package lk.ijse.gdse.DAO.Impl;


import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.DAO.SupplierDAO;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.Entity.Customer;
import lk.ijse.gdse.Entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    public boolean save(Supplier supplier) throws SQLException {
        /*String sql = "INSERT INTO supplier VALUES(?,?,?,?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplierId());
        pstm.setObject(2, supplier.getName());
        pstm.setObject(3, supplier.getDescription());
        pstm.setObject(4, supplier.getAddress());
        pstm.setObject(5, supplier.getTel());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO supplier VALUES(?,?,?,?,?)",supplier.getSupplierId(),supplier.getName(),supplier.getDescription(),supplier.getAddress(),supplier.getTel());
    }

    public ArrayList<Supplier> getAll() throws SQLException {
        /*String sql = "SELECT*FROM supplier";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            String address = resultSet.getString(4);
            String tel = resultSet.getString(5);

            Supplier supplier = new Supplier(id, name, description, address, tel);
            supplierList.add(supplier);
        }
        return supplierList;*/
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT*FROM supplier");
        while (rst.next()){
            Supplier supplier = new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            suppliers.add(supplier);
        }
        return suppliers;
    }

    public boolean delete(String id) throws SQLException {
        /*String sql = "DELETE FROM supplier WHERE supplierId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("DELETE FROM supplier WHERE supplierId = ?",id);
    }

    public boolean update(Supplier supplier) throws SQLException {
        /*String sql = "UPDATE supplier SET name = ? , description = ? , address = ? , tel = ? WHERE supplierId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, supplier.getName());
        pstm.setObject(2, supplier.getDescription());
        pstm.setObject(3, supplier.getAddress());
        pstm.setObject(4, supplier.getTel());
        pstm.setObject(5, supplier.getSupplierId());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("UPDATE supplier SET name = ? , description = ? , address = ? , tel = ? WHERE supplierId = ?",supplier.getName(),supplier.getDescription(),supplier.getAddress(),supplier.getTel(),supplier.getSupplierId());
    }

    public Supplier searchByTel(String id) throws SQLException {
        /*String sql = "SELECT*FROM supplier WHERE supplierId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String supplierId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            String address = resultSet.getString(4);
            String tel = resultSet.getString(5);

            Supplier supplier = new Supplier(supplierId, name, description, address, tel);

            return supplier;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT*FROM supplier WHERE supplierId = ?", id + "");
        if (rst.next()) {
            return new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
        }
        return null;
    }

    public List<String> getTel() throws SQLException {
        /*String sql = "SELECT supplierId from supplier";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> supplierList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            supplierList.add(id);
        }
        return supplierList;*/
        List<String> idList = new ArrayList<>();

        try (ResultSet rst = SQLUtil.execute("SELECT supplierId from supplier")) {
            while (rst.next()) {
                String id = rst.getString(1);
                idList.add(id);
            }
        }
        return idList;
    }


    public String getCurrentId() throws SQLException {
        /*String sql = "SELECT supplierId FROM supplier ORDER BY supplierId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String customerId = resultSet.getString(1);
            return customerId;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT supplierId FROM supplier ORDER BY supplierId DESC LIMIT 1");
        if(rst.next()) {
            String id = rst.getString(1);
            return id;
        }
        return null;
    }
}