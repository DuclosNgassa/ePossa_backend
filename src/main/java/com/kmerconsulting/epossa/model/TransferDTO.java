package com.kmerconsulting.epossa.model;

import java.math.BigDecimal;

public class TransferDTO extends BasisDTO {
    private String sender; //SenderPhone
    private String receiver; //ReceiverPhone
    private BigDecimal amount;
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
