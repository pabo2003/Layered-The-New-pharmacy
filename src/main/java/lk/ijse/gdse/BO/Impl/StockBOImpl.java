package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.StockBO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DAO.StockDAO;
import lk.ijse.gdse.DTO.StockDTO;
import lk.ijse.gdse.Entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);
    @Override
    public boolean saveStock(StockDTO stock) throws SQLException {
        return stockDAO.save(new Stock(stock.getStockId(),stock.getDescription(),stock.getCategory()));
    }

    @Override
    public List<StockDTO> getAllStock() throws SQLException {
        ArrayList<StockDTO> allStock= new ArrayList<>();
        ArrayList<Stock> all = stockDAO.getAll();
        for (Stock s : all) {
            allStock.add(new StockDTO(s.getStockId(),s.getDescription(),s.getCategory()));
        }
        return allStock;
    }

    @Override
    public boolean deleteStock(String id) throws SQLException {
        return stockDAO.delete(id);
    }

    @Override
    public boolean updateStock(Stock stock) throws SQLException {
        return stockDAO.update(new Stock(stock.getStockId(),stock.getDescription(),stock.getCategory()));
    }

    @Override
    public Stock searchById(String id) throws SQLException {
        return stockDAO.searchByTel(id);
    }

    @Override
    public List<String> getId() throws SQLException {
        return stockDAO.getTel();
    }

    @Override
    public String getCurrentId() throws SQLException {
        return stockDAO.getCurrentId();
    }
}
