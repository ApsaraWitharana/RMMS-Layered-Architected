package lk.ijse.D.view.tdm;

public class SupplierTM implements Comparable<SupplierTM> {

    public String su_id;
    public String su_name;
    public String su_address;
    public String contact;
    public String su_email;

    public SupplierTM(String su_id, String su_name, String su_address, String contact, String su_email) {
        this.su_id = su_id;
        this.su_name = su_name;
        this.su_address = su_address;
        this.contact = contact;
        this.su_email = su_email;
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

    public String getSu_address() {
        return su_address;
    }

    public void setSu_address(String su_address) {
        this.su_address = su_address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSu_email() {
        return su_email;
    }

    public void setSu_email(String su_email) {
        this.su_email = su_email;
    }



    @Override
    public String toString() {
        return "SupplierTM{" +
                "su_id='" + su_id + '\'' +
                ", su_name='" + su_name + '\'' +
                ", su_address='" + su_address + '\'' +
                ", contact='" + contact + '\'' +
                ", su_email='" + su_email + '\'' +
                '}';
    }

   @Override
    public int compareTo(SupplierTM o) {
        return 0;
    }
}
