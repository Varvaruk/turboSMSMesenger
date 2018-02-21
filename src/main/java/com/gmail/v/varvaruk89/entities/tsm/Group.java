package com.gmail.v.varvaruk89.entities.tsm;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "GROUPS")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    @ManyToMany(mappedBy = "groups",cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    public Group() {
    }

    public Group(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }


}
