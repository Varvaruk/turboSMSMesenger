package com.gmail.v.varvaruk89.controllers;

import com.gmail.v.varvaruk89.entities.tsm.Group;
import com.gmail.v.varvaruk89.entities.tsm.Message;
import com.gmail.v.varvaruk89.services.tsm.GroupService;
import com.gmail.v.varvaruk89.services.tsm.MessageService;
import com.gmail.v.varvaruk89.services.tsm.SendingMessageToTheGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sending")

public class SendingController {

    @Autowired
    GroupService groupService;

    @Autowired
    MessageService messageService;

    @Autowired
    SendingMessageToTheGroups sendingMessageToTheGroups;

    @RequestMapping
    public String sending(ModelMap modelMap) {
        List<Group> groupList = groupService.getAll();
        List<Message> messageList = messageService.getAllMessages();
        modelMap.addAttribute("groupList", groupList);
        modelMap.addAttribute("messageList", messageList);

        return "sending";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String sending(String messageId, String textMessage, String groupsIds,ModelMap modelMap) {

        List<Group> groupList = groupService.getAll();
        List<Message> messageList = messageService.getAllMessages();
        modelMap.addAttribute("groupList", groupList);
        modelMap.addAttribute("messageList", messageList);

        if(textMessage.equals("")) {
            modelMap.addAttribute("errorText", true);
        return "sending";
        }

        if(groupsIds==null){
            modelMap.addAttribute("errorGroup", true);
            return "sending";
        }

        List<Group> groups = new ArrayList<>();
        String[] groupsId = groupsIds.split(",");
        for (String id:groupsId) {
            groups.add(groupService.getOne(id));
        }
        sendingMessageToTheGroups.sendMessageToGroups(textMessage,groups);


        System.out.println(messageId+textMessage+groupsIds.toString());
        return "redirect:/groups";
    }


}
