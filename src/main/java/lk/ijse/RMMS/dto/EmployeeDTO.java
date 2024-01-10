package lk.ijse.RMMS.dto;

public class EmployeeDTO {
    private String emp_id;
    private String emp_name;
    private String emp_address;
    private String contact;
    private String emp_email;
    private String emp_nic;
    private String jobTitle;
    private String emp_dob;
    private String salary;

    public EmployeeDTO(String emp_id, String name, String emp_dob,String emp_address,String jobTitle,String emp_nic,  String contact, String emp_email,   String salary) {
        this.emp_id = emp_id;
        this.emp_name = name;
        this.emp_dob = emp_dob;
        this.emp_address = emp_address;
        this.jobTitle = jobTitle;
        this.emp_nic = emp_nic;
        this.contact = contact;
        this.emp_email = emp_email;
        this.salary = salary;


    }

    public EmployeeDTO() {

    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getName() {
        return emp_name;
    }

    public void setName(String name) {
        this.emp_name = name;
    }

    public String getAddress() {
        return emp_address;
    }

    public void setAddress(String address) {
        this.emp_address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return emp_email;
    }

    public void setEmail(String email) {
        this.emp_email = email;
    }

    public String getNic() {
        return emp_nic;
    }

    public void setNic(String nic) {
        this.emp_nic = nic;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDob() {
        return emp_dob;
    }

    public void setDob(String dob) {
        this.emp_dob = dob;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
