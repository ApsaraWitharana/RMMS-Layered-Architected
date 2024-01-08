package lk.ijse.D.bo;

import lk.ijse.D.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory=new BOFactory():boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,PLACE_ORDER,SUPPLIER,PLACE_SUPPLIER_ORDER,LOGIN,EMPLOYEE,STOCK,SIGNUP
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER :return new CustomerBOImpl();
            case ITEM: return new ItemBOImpl();
            case PLACE_ORDER: return new PlaceOrderBOImpl();
            case SUPPLIER: return new SupplierBOImpl();
            case PLACE_SUPPLIER_ORDER:return new PlaceSupplierOrderBOImpl();
            case LOGIN:return new LoginBOImpl();
            case EMPLOYEE: return new EmployeeBOImpl();
            case STOCK: return new StockBOImpl();
            case SIGNUP:return new SignUpBOImpl();

            default:return null;
        }
    }
}
