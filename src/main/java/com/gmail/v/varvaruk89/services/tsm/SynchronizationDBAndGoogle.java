package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Group;
import com.gmail.v.varvaruk89.entities.tsm.Student;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface SynchronizationDBAndGoogle {
    void synchronizationDBAndGoogle(Map<Group, List<Student>> groupListMap) throws IOException;

    void groupInDB(Group group);

    void studentInDB(Student student);

    void searchStudentsInTheDB(List<Student> studentList);

    void addingStudentsToTheGroup(Group group, List<Student>studentList);

    void addingGroupsToTheStudents(Group group, List<Student> studentList);
    List<Group> addGroup(Group group, Student student);
}
