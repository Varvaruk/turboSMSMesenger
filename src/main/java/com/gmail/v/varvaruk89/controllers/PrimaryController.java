package com.gmail.v.varvaruk89.controllers;


import com.gmail.v.varvaruk89.entities.tsm.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PrimaryController {

    @RequestMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error",required = false) String error,
                            @RequestParam(value = "logout",required = false) String logout,
                            Model model) {

        model.addAttribute("error",error!=null);
        model.addAttribute("logout",logout!=null);
        return "login";
    }


    @RequestMapping(value = "/")
    public String mainPage(Model model) {
        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
        User user  =  (User) authentication.getPrincipal();
        model.addAttribute("user",user.getUsername());
        model.addAttribute("role",user.getAuthorities());
        return "index";
    }


}
