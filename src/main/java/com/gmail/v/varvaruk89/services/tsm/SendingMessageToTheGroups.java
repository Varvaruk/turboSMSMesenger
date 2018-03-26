package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SendingMessageToTheGroups {

   void sendMessageToGroups(String text, List<Group> groupList);
   List<String> getAllPhoneNumber(List<Group> groupList);



}
