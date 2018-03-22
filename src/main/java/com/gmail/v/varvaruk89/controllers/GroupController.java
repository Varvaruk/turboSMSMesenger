package com.gmail.v.varvaruk89.controllers;

import com.gmail.v.varvaruk89.entities.tsm.Group;
import com.gmail.v.varvaruk89.services.tsm.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {

  @Autowired
    GroupService groupService;
    @RequestMapping
    public String messages(ModelMap modelMap) {
        List<Group>groupList = groupService.getAll();

        modelMap.addAttribute("groups", groupList);
        return "groups";
    }
}
