package com.gmail.v.varvaruk89.controllers;


import com.gmail.v.varvaruk89.entities.tsm.History;
import com.gmail.v.varvaruk89.services.sms.SmsService;
import com.gmail.v.varvaruk89.services.tsm.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @Autowired
    SmsService smsService;



    @RequestMapping
    public String getHistory(ModelMap modelMap) {

        List<History> historyList = historyService.getAll();
        modelMap.addAttribute("historys",historyList);
        modelMap.addAttribute("smsService",smsService);
    System.out.println(smsService.getOne("5403137").toString());
        return "history";
    }
}
