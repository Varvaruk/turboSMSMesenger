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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
