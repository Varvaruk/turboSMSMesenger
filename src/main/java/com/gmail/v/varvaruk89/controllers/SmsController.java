package com.gmail.v.varvaruk89.controllers;


import com.gmail.v.varvaruk89.entities.sms.Sms;
import com.gmail.v.varvaruk89.repo.sms.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SmsController {

    @Autowired
    SmsRepository smsRepository;
    @RequestMapping("/sms")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
       String smstext = "Test otpravki. Vadim";
       String [] pull = {"380936094346"};
       String sign = "Autolux";
        for (String nomer:pull) {
            Sms sms = new Sms(nomer,sign,smstext);
            smsRepository.save(sms);

        }

        return "home";
    }

}
