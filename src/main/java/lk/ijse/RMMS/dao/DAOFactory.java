package lk.ijse.RMMS.dao;

import lk.ijse.RMMS.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;

    }
    public enum DAOTypes{

        CUSTOMER,ITEM,ORDER,ORDER_DETAIL,QUERY,SUPPLIER,SUPPLIER_ORDER,SUPPLIER_ORDER_DETAIL,LOGIN,EMPLOYEE,STOCK,SIGNUP

    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER : return new CustomerDAOImpl();
            case ITEM: return new ItemDAOImpl();
            case ORDER: return new OrderDAOImpl();
            case ORDER_DETAIL: return new OrderDetailDAOImpl();
            case QUERY: return new QueryDAOImpl();
            case SUPPLIER: return new SupplierDAOImpl();
            case SUPPLIER_ORDER:return new SupplierOrderDAOImpl();
            case SUPPLIER_ORDER_DETAIL:return new SupplierOrderDetailDAOImpl();
            case LOGIN:return new LoginDAOImpl();
            case EMPLOYEE:return new EmployeeDAOImpl();
            case STOCK:return new StockDAOImpl();
            case SIGNUP:return new SignUpDAOImpl();


            default:return null;
        }

    }
}
