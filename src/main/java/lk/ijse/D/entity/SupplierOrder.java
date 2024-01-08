package lk.ijse.D.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SupplierOrder {
    private String order_id;

    private LocalDate dueDte;

    private String su_id;

    private String su_name;

    private BigDecimal total;

    public SupplierOrder(String order_id, LocalDate dueDte, String su_id, String su_name, BigDecimal total) {
        this.order_id = order_id;
        this.dueDte = dueDte;
        this.su_id = su_id;
        this.su_name = su_name;
        this.total = total;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public LocalDate getDueDte() {
        return dueDte;
    }

    public void setDueDte(LocalDate dueDte) {
        this.dueDte = dueDte;
    }

    public String getSu_id() {
        return su_id;
    }

    public void setSu_id(String su_id) {
        this.su_id = su_id;
    }

    public String getSu_name() {
        return su_name;
    }

    public void setSu_name(String su_name) {
        this.su_name = su_name;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public SupplierOrder() {

    }

    public SupplierOrder(String order_id, LocalDate dueDte, String su_id) {
        this.order_id = order_id;
        this.dueDte = dueDte;
        this.su_id = su_id;

    }
}
