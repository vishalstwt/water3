package com.commerce.water2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "water2")
public class Water2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phoneNo;
    private String additionalInfo;

    // Default Constructor
    public Water2() {
    }

    // Parameterized Constructor
    public Water2(Long id, String name, String address, String phoneNo, String additionalInfo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.additionalInfo = additionalInfo;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    // toString() method for debugging
    @Override
    public String toString() {
        return "Water2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}

