package com.kmerconsulting.epossa.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "User")
@EntityListeners(AuditingEntityListener.class)
public class User extends BasisDTO {
    @Column(length = 45, nullable = false)
    private String name;
    @Column(length = 45, nullable = false)
    private String phone;
    @Column(length = 45, nullable = false)
    private String password;
    @Column
    private BigDecimal balance;
    @Column(nullable = false)
    private int rating;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
