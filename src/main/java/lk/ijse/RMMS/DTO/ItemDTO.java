package lk.ijse.RMMS.DTO;

import java.io.Serializable;

public class ItemDTO implements Serializable {

    private String item_code;
    private String name;
    private String description;
    private double unit_price;
    private int qty;




    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public ItemDTO(String item_code, String name, String description, double unit_price, int qty) {
        this.item_code = item_code;
        this.name = name;
        this.description = description;
        this.unit_price = unit_price;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "item_code='" + item_code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unit_price=" + unit_price +
                ", qty=" + qty +
                '}';
    }

    public ItemDTO() {

    }
}
