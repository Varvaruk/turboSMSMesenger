package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.sms.Sms;
import com.gmail.v.varvaruk89.entities.tsm.Group;
import com.gmail.v.varvaruk89.entities.tsm.Student;
import com.gmail.v.varvaruk89.entities.tsm.User;
import com.gmail.v.varvaruk89.services.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class SendingMessageToTheGroupsImpl implements SendingMessageToTheGroups {

    @Autowired
    SmsService smsService;
    @Autowired
    StudentService studentService;
    @Autowired
    HistoryService historyService;

    @Value("${sms.sign}")
    public String sign ;

    @Override
    public void sendMessageToGroups(String text, List<Group> groupList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user  =  (User) authentication.getPrincipal();

    List<String> allPhoneNumber  = getAllPhoneNumber(groupList);
        for (String phone:allPhoneNumber) {
            Sms sms = new Sms();
            sms.setMessage(text);
            sms.setSign(sign);
            sms.setNumber(phone);
            sms.setAdded(new Timestamp(System.currentTimeMillis()));
            smsService.save(sms);
            historyService.write(sms,user);

        }

    }

    @Override
    public List<String> getAllPhoneNumber(List<Group> groupList) {
      List <String>  allPhoneNumber = new ArrayList<>();
        for (Group group:groupList) {
            List<Student> studentList = group.getStudents();
            if(studentList.size()>=1){
                for (Student student: studentList) {
                    if(!(student.getPhone()==null)) {
                        allPhoneNumber.addAll(studentService.telephoneNumberConverter(student.getPhone()));
                    }
                    }

                }

        }

        return allPhoneNumber;
    }
}
