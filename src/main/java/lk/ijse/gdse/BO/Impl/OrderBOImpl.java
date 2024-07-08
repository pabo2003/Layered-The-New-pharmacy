package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.OrderBO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DAO.OrderDAO;
import lk.ijse.gdse.Entity.Order;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public String getCurrentId() throws SQLException {
        return orderDAO.getCurrentId();
    }

    @Override
    public boolean saveOrder(Order order) throws SQLException {
        return orderDAO.save(new Order(order.getOrderId(), order.getDescription(), order.getPaymentAmount(), order.getDate(), order.getCuId(), order.getPayId(), order.getEmployeeId()));
    }
}

/*
private String orderId;
private String description;
private double paymentAmount;
private Date date;
private String cuId;
private String payId;
private String employeeId;*/
