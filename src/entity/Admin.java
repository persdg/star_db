package entity;

public class Admin implements User {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    boolean admin = true;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public boolean isAdmin() {
        return admin;
    }
}
