package com.gmail.v.varvaruk89.controllers;

import com.gmail.v.varvaruk89.dao.MessageDao;
import com.gmail.v.varvaruk89.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @Autowired
    MessageDao messageDao;

    @RequestMapping("/newmesage")
    @ResponseBody
    public  String newMessage (String name, String text){
        Message message = new Message(name,text);
        messageDao.save(message);
                return "Message successfully updated!";
    }
}
