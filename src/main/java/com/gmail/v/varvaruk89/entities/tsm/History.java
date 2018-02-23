package com.gmail.v.varvaruk89.entities.tsm;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  int id;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    private Message message;
    @OneToOne(fetch = FetchType.LAZY)
    private Student student;

    private String smsId;


    private Date date;

    public History() {
    }
}
