package com.example.plannerREST.entities;

import com.example.plannerREST.enums.ContactStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private int id;
    @Column(name = "contact_name")
    private String name;
    @Column(name = "contact_position")
    private String position;
    @Column(name = "contact_department")
    private String department;
    @Column(name = "contact_country")
    private String country;
    @Column(name = "contact_email")
    private String email;
    @Column(name = "contact_phone")
    private String phone;
    @Column(name = "contact_status")
    @Enumerated(value = EnumType.STRING)
    private ContactStatus status;
    @Column(name = "contact_consent")
    private String consent;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Activity> activityList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "contacts_companies",
            joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "company_id"))
    private List<Company> companies;


    public Contact() {
    }

    public Contact(String name, String position, String department, String country, String email, String phone, ContactStatus status, String consent, List<Activity> activityList, List<Company> companies) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.consent = consent;
        this.activityList = activityList;
        this.companies = companies;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public ContactStatus getStatus() {
        return status;
    }

    public void setStatus(ContactStatus status) {
        this.status = status;
    }

    public String getConsent() {
        return consent;
    }

    public void setConsent(String consent) {
        this.consent = consent;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", consent='" + consent + '\'' +
                ", activityList=" + activityList +
                ", companies=" + companies +
                '}';
    }
}
