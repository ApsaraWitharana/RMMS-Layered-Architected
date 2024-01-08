package lk.ijse.D.DTO;

public class LoginDTO {

    private String user_name;
    private String password;
    private String email;

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

    @Override
    public String toString() {
        return "LoginDTO{" +
                "name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public LoginDTO() {

    }

    public LoginDTO(String user_name, String password, String email) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
    }
}
