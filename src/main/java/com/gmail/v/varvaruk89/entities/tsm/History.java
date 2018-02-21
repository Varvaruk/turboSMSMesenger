package com.gmail.v.varvaruk89.entities.tsm;

import com.gmail.v.varvaruk89.entities.sms.Sms;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    private Message message;
    @OneToOne(fetch = FetchType.LAZY)
    private Student student;
    @OneToOne(fetch = FetchType.LAZY)
    private Sms sms;
    @OneToOne(fetch = FetchType.LAZY)
    private Date date;

    public History() {
    }
}
