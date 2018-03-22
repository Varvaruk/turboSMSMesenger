package com.gmail.v.varvaruk89.controllers;

import com.gmail.v.varvaruk89.services.google.GoogleJsonClientService;
import com.gmail.v.varvaruk89.services.tsm.SynchronizationDBAndGoogle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class GoogleController {
    @Autowired
    SynchronizationDBAndGoogle synchronizationDBAndGoogle;
    @Autowired
    GoogleJsonClientService googleJsonClientService;

    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/synchronization")
    public String synchronization() throws IOException {
        synchronizationDBAndGoogle.synchronizationDBAndGoogle(googleJsonClientService.getAllGroupsAndStudents());
        //return "redirect:/groups";
        return "redirect:/groups";
    }


}
