package com.gmail.v.varvaruk89.entities.tsm;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STUDENTS")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String phone;
    private String sum;
    private String comment;
    private String notation;
    @ManyToMany
            @JoinTable(name = "StudentGroup",
            joinColumns = {@JoinColumn(name = "stud_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id",referencedColumnName = "id")})
    List<Group> groups = new ArrayList<>();

    public Student() {
    }

    public Student(String name, String phone, String sum, String comment, String notation, List<Group> groups) {
        this.name = name;
        this.phone = phone;
        this.sum = sum;
        this.comment = comment;
        this.notation = notation;
        this.groups = groups;
    }


}
