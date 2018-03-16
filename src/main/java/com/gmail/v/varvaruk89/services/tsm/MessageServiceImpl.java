package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Message;
import com.gmail.v.varvaruk89.repo.tsm.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public void addMessage(Message message) {
messageRepository.save(message);
    }

    @Override
    public void deleteMessage(String messageId) {
messageRepository.delete(Long.parseLong(messageId));
    }

    @Override
    public void editMessage(String messageId,String name,String text) {
    Message message=  messageRepository.findOne(Long.parseLong(messageId));
    message.setName(name);
    message.setText(text);
    messageRepository.save(message);

    }

    @Override
    public List<Message> getAllMessages() {
     List<Message> messageList= (List<Message>) messageRepository.findAll();
        return messageList;
    }
}
