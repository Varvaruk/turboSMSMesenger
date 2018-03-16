package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    void addMessage(Message message);
    void deleteMessage(String messageId);
    void editMessage(String messageId,String name,String text);
    Message getOne (String id);
    List<Message> getAllMessages();



}
