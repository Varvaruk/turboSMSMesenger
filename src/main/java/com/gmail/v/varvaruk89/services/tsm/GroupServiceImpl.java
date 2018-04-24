package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Group;
import com.gmail.v.varvaruk89.repo.tsm.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public Group getOne(String id) {

        return groupRepository.findOne(Long.decode(id));
    }

    @Override
    public Group getByName(String name) {

        List<Group> groupList = (List<Group>) groupRepository.findAll();
        for (Group group : groupList) {
            if (group.getName().equals(name)) {
                return group;
            }
        }

        return null;
    }

    @Override
    public List<Group> getAll() {

       List<Group> groups =  (List<Group>) groupRepository.findAll();

        return groups;
    }

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void delete(Group group) {
        groupRepository.delete(group);
    }
}
