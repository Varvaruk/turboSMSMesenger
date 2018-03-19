package com.gmail.v.varvaruk89.entities.tsm;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToOne(fetch = FetchType.EAGER)
    private Message message;
    @OneToOne(fetch = FetchType.EAGER)
    private Student student;

    private String smsId;


    private Date date;

    public History() {
    }
}
