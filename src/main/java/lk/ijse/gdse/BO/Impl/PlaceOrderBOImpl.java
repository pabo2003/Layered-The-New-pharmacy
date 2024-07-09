package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.PlaceOrderBO;
import lk.ijse.gdse.DAO.*;
import lk.ijse.gdse.DAO.Impl.ItemDAOImpl;
import lk.ijse.gdse.DAO.Impl.OrderDAOImpl;
import lk.ijse.gdse.DAO.Impl.OrderDetailDAOImpl;
import lk.ijse.gdse.DAO.Impl.PaymentDAOImpl;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.DTO.PlaceOrderDTO;
import lk.ijse.gdse.Entity.OrderDetails;
import lk.ijse.gdse.Entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    @Override
    public boolean placeOrder(PlaceOrder po) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            try {
                boolean isPayUpdated = paymentDAO.save(po.getPayment());
                if (isPayUpdated) {
                    boolean isOrderSaved = orderDAO.save(po.getOrder());
                    if (isOrderSaved) {
                        boolean isQtyUpdated = itemDAO.update1(po.getOdList());
                        if (isQtyUpdated) {
                            boolean isOrderDetailSaved = orderDetailDAO.save((OrderDetails) po.getOdList());
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
