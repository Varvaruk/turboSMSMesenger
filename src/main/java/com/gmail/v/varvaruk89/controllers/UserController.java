package com.gmail.v.varvaruk89.controllers;

import com.gmail.v.varvaruk89.entities.tsm.User;
import com.gmail.v.varvaruk89.services.tsm.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping
    public String editThisUser(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        modelMap.addAttribute("user", user);
        // System.out.println("User name = "+ user.getUsername()+" "+("User pass= ")+user.getPassword());
        return "userEdit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editThisUser(@ModelAttribute User user) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            System.out.println(user.getPassword());
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.save(user);
        }
        return "index";
    }


}
