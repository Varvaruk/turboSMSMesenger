package com.gmail.v.varvaruk89.dao;

import com.gmail.v.varvaruk89.model.Message;

import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional

public interface MessageDao extends CrudRepository<Message, Long> {

    Message findByName (String name);
}
