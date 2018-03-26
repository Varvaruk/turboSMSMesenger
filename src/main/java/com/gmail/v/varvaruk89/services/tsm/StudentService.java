package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student getOne(String id);

    Student getByName(String name);
    Student getByPhoneNumber(String phoneNuber);
    List<Student> getAll();
    void save(Student group);
    void delete(Student group);
    List<String>   telephoneNumberConverter(String phone);
}
