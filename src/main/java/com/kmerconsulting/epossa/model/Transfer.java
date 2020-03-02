package com.kmerconsulting.epossa.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Transfer")
@EntityListeners(AuditingEntityListener.class)
public class Transfer extends BasisDTO {
    @Column(nullable = false, length = 45)
    private String sender; //SenderPhone
    @Column(nullable = false, length = 45)
    private String receiver; //ReceiverPhone
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(length = 255)
    private String description;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
