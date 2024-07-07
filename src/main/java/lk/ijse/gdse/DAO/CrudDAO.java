package lk.ijse.gdse.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public  boolean save(T entity) throws SQLException;

    public ArrayList<T> getAll() throws SQLException;

    public  boolean delete(String id) throws SQLException;

    public  boolean update(T entity) throws SQLException;

    public T searchByTel(String tel) throws SQLException;

    public  List<String> getTel() throws SQLException;

    public  String getCurrentId() throws SQLException;
}
