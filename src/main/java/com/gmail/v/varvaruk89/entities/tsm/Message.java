package com.gmail.v.varvaruk89.entities.tsm;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "MESSAGES")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    private String name;

    private String text;

    public Message() {
    }

    public Message(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
