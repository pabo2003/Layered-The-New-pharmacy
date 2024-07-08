package lk.ijse.gdse.DAO;

import lk.ijse.gdse.DAO.Impl.ItemDAOImpl;
import lk.ijse.gdse.DAO.Impl.OrderDAOImpl;
import lk.ijse.gdse.DAO.Impl.PaymentDAOImpl;
import lk.ijse.gdse.DB.DbConnection;
import lk.ijse.gdse.DTO.PlaceOrderDTO;
import lk.ijse.gdse.Entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {
    public static boolean placeOrder(PlaceOrderDTO po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isPayUpdated = PaymentDAOImpl.save(po.getPayment());
            if (isPayUpdated) {
                boolean isOrderSaved = OrderDAOImpl.save(po.getOrder());
                if (isOrderSaved) {
                    boolean isQtyUpdated = ItemDAOImpl.update1(po.getOdList());
                    if (isQtyUpdated) {
                        boolean isOrderDetailSaved = OrderDetailRepo.save(po.getOdList());
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
