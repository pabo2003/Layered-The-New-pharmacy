package lk.ijse.gdse.DAO;

import lk.ijse.gdse.DAO.Impl.CustomerDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        CUSTOMER,EMPLOYEE,ITEM,ORDER,ORDER_DETAILS,PAYMENT,PLACE_ORDER,STOCK,SUPPLIER,SUPPLIER_DETAILS
    }
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            default:
                return null;
        }
    }

}
