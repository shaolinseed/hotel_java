package sample;

import java.util.Date;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String addressLine1;
    private String addressLine2;
    private String postcode;
    private String phoneNumber;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void createCustomersTable(){
        String customersTable = "CREATE TABLE IF NOT EXISTS customers (customerId SERIAL PRIMARY KEY, firstName VARCHAR(50), lastName VARCHAR(50), email VARCHAR(150)" +
                ", country VARCHAR(60), addressLine1 VARCHAR(140), addressLine2 VARCHAR(140), postcode VARCHAR(8), phoneNumber VARCHAR(15));";
        Connect.sqlUpdate(customersTable);
    }


}
