package dev.atslega.cpmf.model;

public class UserData {

    private long id;

    private String lastName;

    private String firstName;

    private String email;

    private String role;

    private String token;

    private Company company;

    public UserData() {
    }

    public UserData(long id, String lastName, String firstName, String email, String role, String token) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", lastName='" + lastName +
                ", firstName='" + firstName +
                ", email='" + email +
                '}';
    }
}
