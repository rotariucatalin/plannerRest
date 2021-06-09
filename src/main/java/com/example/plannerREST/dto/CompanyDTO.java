package com.example.plannerREST.dto;

import com.example.plannerREST.enums.CompanyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "Details for companyDTO")
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
    private ContactDTO salesContactDTO;
    private List<ContactDTO> contactDTOList;

    public CompanyDTO() {
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

    public ContactDTO getSalesContactDTO() {
        return salesContactDTO;
    }

    public void setSalesContactDTO(ContactDTO salesContactDTO) {
        this.salesContactDTO = salesContactDTO;
    }

    public List<ContactDTO> getContactDTOList() {
        return contactDTOList;
    }

    public void setContactDTOList(List<ContactDTO> contactDTOList) {
        this.contactDTOList = contactDTOList;
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
                ", salesContact=" + salesContactDTO +
                ", contacts=" + contactDTOList +
                '}';
    }
}
