package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Group;
import com.gmail.v.varvaruk89.entities.tsm.Student;
import com.gmail.v.varvaruk89.services.google.GoogleJsonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class SynchronizationDBAndGoogleImpl implements SynchronizationDBAndGoogle {

    @Autowired
    GoogleJsonClientService googleJsonClientService;
    @Autowired
    GroupService groupService;
    @Autowired
    StudentService studentService;


    @Override
    public void synchronizationDBAndGoogle(Map<Group, List<Student>> groupListMap) throws IOException {


        for (Map.Entry<Group, List<Student>> entry : groupListMap.entrySet()) {
            //  System.out.println(entry.getKey() + ":" + entry.getValue());
            groupInDB(entry.getKey());
            groupService.save(entry.getKey());
            searchStudentsInTheDB(entry.getValue());
            addingStudentsToTheGroup(entry.getKey(), entry.getValue());
            addingGroupsToTheStudents(entry.getKey(), entry.getValue());


        }


    }

    @Override
    public void groupInDB(Group group) {

        if (!(groupService.getByName(group.getName()) == null)) {
            group.setId(groupService.getByName(group.getName()).getId());

        }

    }

    @Override
    public void studentInDB(Student student) {
        if (!((studentService.getByPhoneNumber(student.getPhone())) == null)) {
            student.setId(studentService.getByPhoneNumber(student.getPhone()).getId());
            student.setGroups(studentService.getByPhoneNumber(student.getPhone()).getGroups());
        }
    }

    @Override
    public void searchStudentsInTheDB(List<Student> studentList) {
        for (Student student : studentList) {
            studentInDB(student);
        }
    }

    @Override
    public void addingStudentsToTheGroup(Group group, List<Student> studentList) {
        group.setStudents(studentList);
    }


    @Override
    public void addingGroupsToTheStudents(Group group, List<Student> studentList) {

        for (Student student : studentList) {
            //  groupList.add(group);
            student.setGroups(addGroup(group, student));
            studentService.save(student);
        }
    }

    public List<Group> addGroup(Group group, Student student) {
//        Set<Group> groupSet = new LinkedHashSet<>(student.getGroups());
//        groupSet.add(group);
//        List<Group> groupList = new ArrayList<>(groupSet);

        List<Group> groupList = student.getGroups();
        for (Group groupMy : groupList) {
            if (groupMy.getName().equals(group.getName())) {
                return groupList;

            }

        }
        groupList.add(group);

        return groupList;
    }

}


