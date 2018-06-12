package entity;

public class NormalUser implements User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    boolean admin = false;

    public NormalUser(String username, String password, String name, String surname, String email, boolean admin) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
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
