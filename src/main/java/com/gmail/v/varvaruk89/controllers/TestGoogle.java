package com.gmail.v.varvaruk89.controllers;

import com.gmail.v.varvaruk89.services.google.GoogleJsonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class TestGoogle {
    @Autowired
    GoogleJsonClientService googleJsonClientService;

    @RequestMapping("/test")

    public String getIds () throws IOException {
      System.out.println( googleJsonClientService.synchronizationGroupOfGOOGLE());

        return "greeting";
    }


}
