package lk.ijse.gdse.BO;

import lk.ijse.gdse.BO.Impl.CustomerBOImpl;

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
            default:
                return null;
        }
    }
}
