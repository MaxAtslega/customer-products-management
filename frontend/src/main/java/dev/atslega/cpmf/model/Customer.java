package dev.atslega.cpmf.model;


public class Customer {

    // Getter-Methoden
    private String firstName;
    private String lastName;
    private String id;
    private String mail;
    private String phoneNumber;
    private String address;
    private String companyName;

    public Customer(String firstName, String lastName, String id, String mail, String phoneNumber, String address, String companyName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.companyName = companyName;
    }

    public String getID() {
        return id;
    }

    public void setID(String iD) {
        this.id = iD;
    }

    public String getFirstName() {
        return firstName;
    }

    // Setter-Methoden
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

