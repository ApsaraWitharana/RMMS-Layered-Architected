package lk.ijse.D.view.tdm;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderTM {

    private String order_id;

    private LocalDate dueDte;

    private String cu_id;

    private String cu_name;

    private BigDecimal total;

    public OrderTM(String order_id, LocalDate dueDte, String cu_id, String cu_name, BigDecimal total) {
        this.order_id = order_id;
        this.dueDte = dueDte;
        this.cu_id = cu_id;
        this.cu_name = cu_name;
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

    public String getCu_id() {
        return cu_id;
    }

    public void setCu_id(String cu_id) {
        this.cu_id = cu_id;
    }

    public String getCu_name() {
        return cu_name;
    }

    public void setCu_name(String cu_name) {
        this.cu_name = cu_name;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public OrderTM() {

    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "order_id='" + order_id + '\'' +
                ", dueDte=" + dueDte +
                ", cu_id='" + cu_id + '\'' +
                ", cu_name='" + cu_name + '\'' +
                ", total=" + total +
                '}';
    }
}
