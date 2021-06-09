package com.example.plannerREST.dto;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "Details for usersDTO")
public class UsersDTO implements Serializable {

    private static final long serialVersionUID = 9178661439383356177L;

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private boolean active;
    List<PermissionDTO> permissions;

    public UsersDTO() {
    }

    public UsersDTO(int id, String firstName, String lastName, String userName, String email, String password, boolean active, List<PermissionDTO> permissions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.permissions = permissions;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "UsersDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", permissions=" + permissions +
                '}';
    }
}
