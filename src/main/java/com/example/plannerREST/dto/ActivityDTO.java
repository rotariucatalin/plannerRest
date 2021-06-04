package com.example.plannerREST.dto;

import com.example.plannerREST.enums.ActivityStatus;
import com.example.plannerREST.enums.ActivityType;

import java.io.Serializable;

public class ActivityDTO implements Serializable {

    private static final long serialVersionUID = 9178661439383356177L;

    private int id;
    private String subject;
    private String reference;
    private ActivityType type;
    private String date;
    private ActivityStatus status;
    private String location;
    private ContactDTO contact;

    public ActivityDTO() {
    }

    public ActivityDTO(int id, String subject, String reference, ActivityType type, String date, ActivityStatus status, String location) {
        this.id = id;
        this.subject = subject;
        this.reference = reference;
        this.type = type;
        this.date = date;
        this.status = status;
        this.location = location;
    }

    public ActivityDTO(int id, String subject, String reference, ActivityType type, String date, ActivityStatus status, String location, ContactDTO contact) {
        this.id = id;
        this.subject = subject;
        this.reference = reference;
        this.type = type;
        this.date = date;
        this.status = status;
        this.location = location;
        this.contact = contact;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ContactDTO getContact() {
        return contact;
    }

    public void setContact(ContactDTO contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "ActivityDTO{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", reference='" + reference + '\'' +
                ", type=" + type +
                ", date='" + date + '\'' +
                ", status=" + status +
                ", location='" + location + '\'' +
                ", contact=" + contact +
                '}';
    }
}
