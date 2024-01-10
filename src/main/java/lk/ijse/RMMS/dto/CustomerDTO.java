package lk.ijse.RMMS.dto;

public class CustomerDTO {
    private String cu_id;
    private String cu_name;
    private String address;
    private String cu_contact;
    private String cu_email;

    public CustomerDTO(String cu_id, String cu_name, String address, String cu_contact, String cu_email) {
        this.cu_id = cu_id;
        this.cu_name = cu_name;
        this.address = address;
        this.cu_contact = cu_contact;
        this.cu_email = cu_email;
    }

    public CustomerDTO() {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCu_contact() {
        return cu_contact;
    }

    public void setCu_contact(String cu_contact) {
        this.cu_contact = cu_contact;
    }

    public String getCu_email() {
        return cu_email;
    }

    public void setCu_email(String cu_email) {
        this.cu_email = cu_email;
    }
}
