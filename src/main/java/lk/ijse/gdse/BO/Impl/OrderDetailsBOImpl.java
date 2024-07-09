package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.OrderDetailsBO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DAO.OrderDAO;
import lk.ijse.gdse.DAO.OrderDetailDAO;
import lk.ijse.gdse.DAO.SQLUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    @Override
    public boolean save(List<OrderDetails> odList) throws SQLException {
        return orderDetailDAO.save(new OrderDetails());
    }

    @Override
    public boolean saveOrderDetail(OrderDetails od) throws SQLException {
        return false;
    }
}
