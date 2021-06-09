package com.example.plannerREST.dto;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "Details for permissions")
public class PermissionDTO implements Serializable {

    private static final long serialVersionUID =9178661439383356177L;

    private int id;
    private String name;

    public PermissionDTO() {
    }

    public PermissionDTO(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "PermissionDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
