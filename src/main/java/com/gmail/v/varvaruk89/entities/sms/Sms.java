package com.gmail.v.varvaruk89.entities.sms;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "aluxsms")
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //@Column(name = "msg_id")
    @Transient
    private String msgId;

    @Column
    private String number;

    @Column
    private String sign;

    @Column
    private String message;

    @Transient
    private String wappush;

    @Column(name = "is_flash")
    private Boolean isFlash = false;

    @Transient
    private Double cost;

    @Transient
    private Double balance;

    @Transient
    private Timestamp added;

    @Column(name = "send_time")
    private Timestamp sendTime;

    @Transient
    private Timestamp sended;

    @Transient
    private Timestamp received;

    //@Column(name = "error_code")
    @Transient
    private String errorCode;

    //@Column
    @Transient
    private String status;

    public Sms() {
    }

    public Sms(String number, String sign, String message) {
        this.number = number;
        this.sign = sign;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWappush() {
        return wappush;
    }

    public void setWappush(String wappush) {
        this.wappush = wappush;
    }

    public Boolean getFlash() {
        return isFlash;
    }

    public void setFlash(Boolean flash) {
        isFlash = flash;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Timestamp getAdded() {
        return added;
    }

    public void setAdded(Timestamp added) {
        this.added = added;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public Timestamp getSended() {
        return sended;
    }

    public void setSended(Timestamp sended) {
        this.sended = sended;
    }

    public Timestamp getReceived() {
        return received;
    }

    public void setReceived(Timestamp received) {
        this.received = received;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}