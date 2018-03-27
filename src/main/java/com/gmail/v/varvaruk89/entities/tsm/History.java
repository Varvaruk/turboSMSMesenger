package com.gmail.v.varvaruk89.entities.tsm;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    private String sms;
    private Timestamp date;

    public History() {
    }

    public History(User user, String sms, Timestamp date) {
        this.user = user;
        this.sms = sms;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
