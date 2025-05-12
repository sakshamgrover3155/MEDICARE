// models/User.java
package models;

public abstract class User {
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = "";
        this.email = "";
        this.phone = "";
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean verifyPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}







