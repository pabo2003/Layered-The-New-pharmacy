package lk.ijse.gdse.BO;

import lk.ijse.gdse.DTO.StockDTO;
import lk.ijse.gdse.Entity.Stock;

import java.sql.SQLException;
import java.util.List;

public interface StockBO extends SuperBO{

    public boolean saveStock(StockDTO stock) throws SQLException;

    public List<StockDTO> getAllStock() throws SQLException;

    public boolean deleteStock(String id) throws SQLException;

    public boolean updateStock(StockDTO stockDTO) throws SQLException;

    public Stock searchById(String id) throws SQLException;

    public List<String> getId() throws SQLException;

    public String getCurrentId() throws SQLException;
}
