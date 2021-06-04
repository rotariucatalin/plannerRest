package com.example.plannerREST.entities;

import com.example.plannerREST.enums.CompanyType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;
    @Column(name = "company_name")
    private String name;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "company_type")
    private CompanyType type;
    @Column(name = "company_email")
    private String email;
    @Column(name = "company_phone")
    private String phone;
    @Column(name = "company_post_address")
    private String postAddress;
    @Column(name = "company_city")
    private String city;
    @Column(name = "company_website")
    private String website;
    @Column(name = "company_description")
    private String description;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_sales_company")
    private Company salesCompany;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_sales_person")
    private Contact salesContact;

    @ManyToMany(mappedBy = "companies", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Contact> contacts;

    public Company() {
    }

    public Company(String name, CompanyType type, String email, String phone, String postAddress, String city, String website, String description, Company salesCompany, Contact salesContact) {
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

    public Company getSalesCompany() {
        return salesCompany;
    }

    public void setSalesCompany(Company salesCompany) {
        this.salesCompany = salesCompany;
    }

    public Contact getSalesContact() {
        return salesContact;
    }

    public void setSalesContact(Contact salesContact) {
        this.salesContact = salesContact;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
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
