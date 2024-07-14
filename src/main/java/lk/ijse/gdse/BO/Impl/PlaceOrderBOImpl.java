package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.ItemBO;
import lk.ijse.gdse.BO.PaymentBO;
import lk.ijse.gdse.BO.PlaceOrderBO;
import lk.ijse.gdse.DAO.*;
import lk.ijse.gdse.DAO.Impl.ItemDAOImpl;
import lk.ijse.gdse.DAO.Impl.OrderDAOImpl;
import lk.ijse.gdse.DAO.Impl.OrderDetailDAOImpl;
import lk.ijse.gdse.DAO.Impl.PaymentDAOImpl;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.DTO.OrderDTO;
import lk.ijse.gdse.DTO.OrderDetailsDTO;
import lk.ijse.gdse.DTO.PlaceOrderDTO;
import lk.ijse.gdse.Entity.Order;
import lk.ijse.gdse.Entity.OrderDetails;
import lk.ijse.gdse.Entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public String getCurrentId() throws SQLException {
        return orderDAO.getCurrentId();
    }

    @Override
    public boolean saveOrder(OrderDTO order) throws SQLException {
        return orderDAO.save(new Order(order.getOrderId(), order.getDescription(), order.getPaymentAmount(), order.getDate(), order.getCuId(), order.getPayId(), order.getEmployeeId()));
    }

    @Override
    public boolean save(List<OrderDetailsDTO> odList) throws SQLException {
        for (OrderDetailsDTO od : odList) {
            boolean isSaved = saveOrderDetail(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderDetail(OrderDetailsDTO od) throws SQLException {
        return orderDetailDAO.save(new OrderDetails(od.getItemId(),od.getOrderId(),od.getQty(),od.getUnitPrice()));
    }

    @Override
    public boolean placeOrder(PlaceOrderDTO po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        PaymentBO paymentBO =  new PaymentBOImpl();
        PlaceOrderBO placeOrderBO =  new PlaceOrderBOImpl();
        ItemBO itemBO =  new ItemBOImpl();


        try {
            boolean isPayUpdated = paymentBO.savePayment(po.getPayment());
            if (isPayUpdated) {
                boolean isOrderSaved = placeOrderBO.saveOrder(po.getOrder());
                if (isOrderSaved) {
                    boolean isQtyUpdated = itemBO.update1(po.getOdList());
                    if (isQtyUpdated) {
                        boolean isOrderDetailSaved = placeOrderBO.save(po.getOdList());
                        if (isOrderDetailSaved) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
