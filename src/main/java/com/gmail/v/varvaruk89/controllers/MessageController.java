package com.gmail.v.varvaruk89.controllers;

import com.gmail.v.varvaruk89.entities.tsm.Message;
import com.gmail.v.varvaruk89.services.tsm.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/messages")
    public String messages(ModelMap modelMap) {
        List<Message> messageList = messageService.getAllMessages();
        modelMap.addAttribute("messages", messageList);
        return "messages";
    }


    @RequestMapping("/newmesage")

    public String newMessage() {

        String name = "nameMSM";
        String text = "messageTEXT";
        Message message = new Message(name, text);
        messageService.addMessage(message);
        return "index";
    }
}
