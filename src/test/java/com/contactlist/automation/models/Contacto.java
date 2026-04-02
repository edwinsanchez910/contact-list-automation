package com.contactlist.automation.models;

public class Contacto {

    private final String firstName;
    private final String lastName;
    private final String birthdate;
    private final String email;
    private final String phone;
    private final String street1;
    private final String street2;
    private final String city;
    private final String stateProvince;
    private final String postalCode;
    private final String country;

    public Contacto(String firstName, String lastName, String birthdate, String email, String phone,
                    String street1, String street2, String city, String stateProvince,
                    String postalCode, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.stateProvince = stateProvince;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getCity() {
        return city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
