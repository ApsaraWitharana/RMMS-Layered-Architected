package lk.ijse.D.view.tdm;

public class StockTM implements Comparable<StockTM>{
    private  String id;
    private String material_name;
    private  int  qty;
    private String description;
    private double  unit_price;

    public StockTM(String id, String material_name, int qty, String description, double unit_price) {
        this.id = id;
        this.material_name = material_name;
        this.qty = qty;
        this.description = description;
        this.unit_price = unit_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    @Override
    public String toString() {
        return "StockTM{" +
                "id='" + id + '\'' +
                ", material_name='" + material_name + '\'' +
                ", qty=" + qty +
                ", description='" + description + '\'' +
                ", unit_price=" + unit_price +
                '}';
    }


    @Override
    public int compareTo(StockTM o) {
        return 0;
    }
}
