package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Student;

import java.util.List;

public interface StudentService {
    Student getOne(String id);

    Student getByName(String name);

    List<Student> getAll();

    void save(Student group);

    void delete(Student group);
}
