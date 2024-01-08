package lk.ijse.D.view.tdm;

import java.math.BigDecimal;

public class OrderDetailTM {

    private String item_code;
    private String description;
    private int qty;
    private double unit_price;
    private BigDecimal total;

    public OrderDetailTM() {

    }

    public OrderDetailTM(String item_code, String description, int qty, double unit_price, BigDecimal total) {
        this.item_code = item_code;
        this.description = description;
        this.qty = qty;
        this.unit_price = unit_price;
        this.total = total;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailTM{" +
                "item_code='" + item_code + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", unit_price=" + unit_price +
                ", total=" + total +
                '}';
    }
}
