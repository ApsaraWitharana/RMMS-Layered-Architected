package lk.ijse.RMMS.entity;

public class Login {

    private String user_name;
    private String password;
    private String email;

    public Login(String user_name, String password, String email) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
    }

    public Login(String userName, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    public String getName() {
        return user_name;
    }

    public void setName(String name) {
        this.user_name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
