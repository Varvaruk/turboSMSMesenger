package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.sms.Sms;
import com.gmail.v.varvaruk89.entities.tsm.Group;
import com.gmail.v.varvaruk89.entities.tsm.Student;
import com.gmail.v.varvaruk89.services.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SendingMessageToTheGroupsImpl implements SendingMessageToTheGroups {

    @Autowired
    SmsService smsService;
    @Autowired
    StudentService studentService;

    @Value("${sms.sign}")
    public String sign ;

    @Override
    public void sendMessageToGroups(String text, List<Group> groupList) {


    List<String> allPhoneNumber  = getAllPhoneNumber(groupList);
        for (String phone:allPhoneNumber) {
            Sms sms = new Sms();
            sms.setMessage(text);
            sms.setSign(sign);
            sms.setNumber(phone);
            smsService.save(sms);
        }



    }

    @Override
    public List<String> getAllPhoneNumber(List<Group> groupList) {
      List <String>  allPhoneNumber = new ArrayList<>();
        for (Group group:groupList) {
            List<Student> studentList = group.getStudents();
            if(studentList.size()>1){
                for (Student student: studentList) {
                  allPhoneNumber.addAll(studentService.telephoneNumberConverter(student.getPhone()));
               }
            }
        }

        return allPhoneNumber;
    }
}
