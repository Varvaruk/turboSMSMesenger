package com.gmail.v.varvaruk89.repo.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
