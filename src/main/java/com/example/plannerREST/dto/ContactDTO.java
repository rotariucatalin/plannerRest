package com.example.plannerREST.dto;

import com.example.plannerREST.enums.ContactStatus;

import java.io.Serializable;
import java.util.List;

public class ContactDTO implements Serializable {

    private static final long serialVersionUID = 9178661439383356177L;

    private int id;
    private String name;
    private String position;
    private String department;
    private String country;
    private String email;
    private String phone;
    private ContactStatus status;
    private String consent;
    private List<ActivityDTO> activityList;
    private List<CompanyDTO> company;

    public ContactDTO() {
    }

    public ContactDTO(int id, String name, String position, String department, String country, String email, String phone, ContactStatus status, String consent) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.consent = consent;
    }

    public ContactDTO(int id, String name, String position, String department, String country, String email, String phone, ContactStatus status, String consent, List<ActivityDTO> activityList, List<CompanyDTO> company) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.consent = consent;
        this.activityList = activityList;
        this.company = company;
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

    public List<ActivityDTO> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityDTO> activityList) {
        this.activityList = activityList;
    }

    public List<CompanyDTO> getCompany() {
        return company;
    }

    public void setCompany(List<CompanyDTO> company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
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
                ", company=" + company +
                '}';
    }
}
