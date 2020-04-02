package com.kmerconsulting.epossa.model;

public class ResetPassword {
    String phone;
    String email;
    String temppassword;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTemppassword() {
        return temppassword;
    }

    public void setTemppassword(String temppassword) {
        this.temppassword = temppassword;
    }
}