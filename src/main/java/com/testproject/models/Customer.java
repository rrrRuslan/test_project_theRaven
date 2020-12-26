package com.testproject.models;

import java.util.Date;

public class Customer {

    Long id;
    Long created;
    Long updated;
    String full_name;
    String email;
    String phone;



    public Customer() {
    }

    public Customer(Long id, Long updated, String full_name, String email, String phone) {
        this.id = id;
        this.created = new Date().getTime();
        this.updated = updated;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
    }

    public Customer(Long id, String full_name, String email, String phone) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}' + '\n';
    }




}
