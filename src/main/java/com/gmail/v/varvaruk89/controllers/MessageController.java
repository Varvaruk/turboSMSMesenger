package com.gmail.v.varvaruk89.controllers;

import com.gmail.v.varvaruk89.dao.MessageDao;
import com.gmail.v.varvaruk89.entities.tsm.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {

    @Autowired
    MessageDao messageDao;

    @RequestMapping("/newmesage")

    public  String newMessage (){

        String name = "nameMSM";
        String text = "messageTEXT";
        Message message = new Message(name,text);
        messageDao.save(message);
                return "Message successfully updated!";
    }
}
