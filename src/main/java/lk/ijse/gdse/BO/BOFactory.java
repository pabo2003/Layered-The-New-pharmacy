package lk.ijse.gdse.BO;

import lk.ijse.gdse.BO.Impl.*;
import lk.ijse.gdse.DAO.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOType{
        CUSTOMER,EMPLOYEE,ITEM,ORDER,ORDER_DETAILS,PAYMENT,PLACE_ORDER,STOCK,SUPPLIER,SUPPLIER_DETAILS
    }

    public SuperBO getBO(BOType boType) {
        switch (boType) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case STOCK:
                return new StockBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SUPPLIER_DETAILS:
                return new SupplierDetailsBOImpl();
            default:
                return null;
        }
    }
}
