package com.entities;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String contactName;
    private String phone;

    // Default constructor
    public Contact() {
    }

    // Parameterized constructor
    public Contact(String contactName, String phone) {
        this.contactName = contactName;
        this.phone = phone;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
