package com.example.plannerREST.entities;

import com.example.plannerREST.enums.ActivityStatus;
import com.example.plannerREST.enums.ActivityType;

import javax.persistence.*;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private int id;
    @Column(name = "activity_subject")
    private String subject;
    @Column(name = "activity_reference")
    private String reference;
    @Column(name = "activity_type")
    @Enumerated(EnumType.STRING)
    private ActivityType type;
    @Column(name = "activity_date")
    private String date;
    @Column(name = "activity_status")
    @Enumerated(EnumType.STRING)
    private ActivityStatus status;
    @Column(name = "activity_location")
    private String location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "activity_contact")
    private Contact contact;

    public Activity() {
    }

    public Activity(String subject, String reference, ActivityType type, String date, ActivityStatus status, String location, Contact contact) {
        this.subject = subject;
        this.reference = reference;
        this.type = type;
        this.date = date;
        this.status = status;
        this.location = location;
        this.contact = contact;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
