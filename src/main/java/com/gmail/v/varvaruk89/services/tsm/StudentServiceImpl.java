package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Student;
import com.gmail.v.varvaruk89.repo.tsm.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student getOne(String id) {
        return studentRepository.findOne(Long.decode(id));
    }

    @Override
    public Student getByName(String name) {
        List<Student> studentList = (List<Student>) studentRepository.findAll();
        for (Student student : studentList) {
            if (student.getName().equals(name)) {
                return student;
            }
        }

        return null;
    }

    @Override
    public List<Student> getAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }
}