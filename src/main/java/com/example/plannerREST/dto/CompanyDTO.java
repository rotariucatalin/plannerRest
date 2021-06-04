package com.example.plannerREST.dto;

import com.example.plannerREST.enums.CompanyType;

import java.io.Serializable;
import java.util.List;

public class CompanyDTO implements Serializable {

    private static final long serialVersionUID = 9178661439383356177L;

    private int id;
    private String name;
    private CompanyType type;
    private String email;
    private String phone;
    private String postAddress;
    private String city;
    private String website;
    private String description;
    private CompanyDTO salesCompany;
    private ContactDTO salesContact;
    private List<ContactDTO> contacts;

    public CompanyDTO() {
    }

    public CompanyDTO(int id, String name, CompanyType type, String email, String phone, String postAddress, String city, String website, String description, CompanyDTO salesCompany, ContactDTO salesContact, List<ContactDTO> contacts) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.email = email;
        this.phone = phone;
        this.postAddress = postAddress;
        this.city = city;
        this.website = website;
        this.description = description;
        this.salesCompany = salesCompany;
        this.salesContact = salesContact;
        this.contacts = contacts;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
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

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CompanyDTO getSalesCompany() {
        return salesCompany;
    }

    public void setSalesCompany(CompanyDTO salesCompany) {
        this.salesCompany = salesCompany;
    }

    public ContactDTO getSalesContact() {
        return salesContact;
    }

    public void setSalesContact(ContactDTO salesContact) {
        this.salesContact = salesContact;
    }

    public List<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", postAddress='" + postAddress + '\'' +
                ", city='" + city + '\'' +
                ", website='" + website + '\'' +
                ", description='" + description + '\'' +
                ", salesCompany=" + salesCompany +
                ", salesContact=" + salesContact +
                ", contacts=" + contacts +
                '}';
    }
}
