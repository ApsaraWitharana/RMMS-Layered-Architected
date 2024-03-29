package lk.ijse.RMMS.dto;

import java.io.Serializable;

public class OrderDetailDTO implements Serializable {

    private String order_id;
    private String item_code;
    private int qty;
    private double unit_price;



    public OrderDetailDTO() {

    }

    public OrderDetailDTO(String item_code,int qty, double unit_price) {
        this.item_code = item_code;
        this.qty = qty;
        this.unit_price = unit_price;
    }

//    public OrderDetailDTO(String orderId, String itemCode, int qty, double unitPrice) {
//        this.order_id = order_id;
//        this.item_code = item_code;
//        this.qty = qty;
//        this.unit_price = unit_price;
//    }


    public OrderDetailDTO(String order_id, String item_code, int qty, double unit_price) {

        this.order_id = order_id;
        this.item_code = item_code;
        this.qty = qty;
        this.unit_price = unit_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "order_id='" + order_id + '\'' +
                ", item_code='" + item_code + '\'' +
                ", qty=" + qty +
                ", unit_price=" + unit_price +
                '}';
    }
}
