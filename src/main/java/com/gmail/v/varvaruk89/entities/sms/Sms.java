package com.gmail.v.varvaruk89.entities.sms;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@Entity
@Table(name = "progkievua1")
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}