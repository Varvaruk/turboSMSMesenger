package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
    Group getOne(String id);

    Group getByName(String name);

    List<Group> getAll();

    void save(Group group);

    void delete(Group group);

}
