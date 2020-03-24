package com.kmerconsulting.epossa.model;

public class UserPassword {
    //@Size(min = 3, max = 45)
    private String phone;
    //@Size(min = 3, max = 300)
    private String oldPassword;
    //@Size(min = 3, max = 300)
    private String newPassword;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
