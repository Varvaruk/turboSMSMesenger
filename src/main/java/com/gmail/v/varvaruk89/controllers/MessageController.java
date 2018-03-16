package com.gmail.v.varvaruk89.controllers;

import com.gmail.v.varvaruk89.entities.tsm.Message;
import com.gmail.v.varvaruk89.services.tsm.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

   @RequestMapping
    public String messages(ModelMap modelMap) {
        List<Message> messageList = messageService.getAllMessages();
        modelMap.addAttribute("messages", messageList);
        return "messages";
    }


    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String editMessage(@RequestParam("id") String id, ModelMap modelMap){

      Message message = messageService.getOne(id);
      modelMap.addAttribute("message",message);

       return "messageForm";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String saveEditMessage(@ModelAttribute Message message){

       messageService.addMessage(message);

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
