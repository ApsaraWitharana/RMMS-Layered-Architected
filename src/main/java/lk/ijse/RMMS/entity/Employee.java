package lk.ijse.RMMS.entity;

public class Employee {

    private  String salary;
    private String emp_id;
    private String emp_name;
    private String emp_address;
    private String contact;
    private String emp_email;
    private String emp_nic;
    private String job_title;
    private String emp_dob;

    public Employee(String emp_id, String emp_name,String emp_dob, String emp_address,String jobTitle,String emp_nic, String contact, String emp_email,  String salary) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_dob = emp_dob;
        this.emp_address = emp_address;
        this.job_title = jobTitle;
        this.emp_nic = emp_nic;
        this.contact = contact;
        this.emp_email = emp_email;
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_address() {
        return emp_address;
    }

    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    public String getEmp_nic() {
        return emp_nic;
    }

    public void setEmp_nic(String emp_nic) {
        this.emp_nic = emp_nic;
    }

    public String getJobTitle() {
        return job_title;
    }

    public void setJobTitle(String jobTitle) {
        this.job_title = jobTitle;
    }

    public String getEmp_dob() {
        return emp_dob;
    }

    public void setEmp_dob(String emp_dob) {
        this.emp_dob = emp_dob;
    }

    public Employee() {

    }
}
