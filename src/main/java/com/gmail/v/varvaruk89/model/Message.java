package com.gmail.v.varvaruk89.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MESSAGES")
public class Message implements Serializable {
@Id @GeneratedValue(strategy = GenerationType.AUTO)
@Getter
private int id;
@Getter @Setter
private String name;
@Getter @Setter
private String text;






}
