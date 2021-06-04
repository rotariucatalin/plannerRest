package com.example.plannerREST.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private int id;
    @Column(name = "permission_name")
    private String name;

    @ManyToMany(mappedBy = "permissions")
    List<Users> usersList;

    public Permission() {
    }

    public Permission(String name, List<Users> usersList) {
        this.name = name;
        this.usersList = usersList;
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

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
}
