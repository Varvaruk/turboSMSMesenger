package com.gmail.v.varvaruk89.entities.tsm;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String login;
    private String password;
    private String userRole ;

    public User() {
    }
}
