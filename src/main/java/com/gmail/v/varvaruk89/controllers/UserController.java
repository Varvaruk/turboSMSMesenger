package com.gmail.v.varvaruk89.controllers;

import com.gmail.v.varvaruk89.entities.tsm.Role;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

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
        System.out.println(user.getAuthorities().stream().map(Role::getAuthority).collect(joining(",")));
        return "userEdit";
    }


    @RequestMapping(value = "/editMyProfile", method = RequestMethod.POST)
    public String editMyProfile(@RequestParam("id") String id, @ModelAttribute User user, ModelMap modelMap) {

//     System.out.println(user.getPassword().getBytes().length);
        if (user.getUsername().getBytes().length <= 3) {
            modelMap.addAttribute("usernameEror", true);
            return "userEdit";
        }
        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            modelMap.addAttribute("passEror", true);
            return "userEdit";
        }
        if (user.getPassword().getBytes().length < 6) {
            modelMap.addAttribute("passErorcharacters", true);
            return "userEdit";
        }

        if (user.getPassword().equals(user.getConfirmPassword()) & user.getUsername().getBytes().length >= 3) {
            System.out.println(user);
            userService.editUserPassword(id, user.getPassword());
            userService.editUserUsername(id, user.getUsername());
            return "redirect:../logout";
        }

        return "userEdit";
    }


    @RequestMapping(value = "/management")
    public String userManagement(ModelMap modelMap) {
        List<User> userList = userService.getAllUsers();
        modelMap.addAttribute("userList", userList);
        return "userManagement";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String userEditForm(@RequestParam("id") String id, ModelMap modelMap) {
        User user = userService.getUserById(id);
        user.setConfirmPassword(user.getPassword());
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("role", Role.values());
        modelMap.addAttribute("usernameEror", false);
        modelMap.addAttribute("passEror", false);
        modelMap.addAttribute("passErorcharacters", false);
        return "userManagementEdit";

    }

    @RequestMapping(value = "/add")
    public String userAdd(ModelMap modelMap) {
        User user = new User();

        modelMap.addAttribute("user", user);
        modelMap.addAttribute("role", Role.values());
        modelMap.addAttribute("usernameEror", false);
        modelMap.addAttribute("passEror", false);
        return "userAdd";
    }


    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    public String userUpdate(@RequestParam("id") String id, @ModelAttribute User user, ModelMap modelMap) {
        System.out.println(user);
        System.out.println(id);
        modelMap.addAttribute("role", Role.values());
        Optional<User> userFaund = userService.findByUsername(user.getUsername());
        if (userFaund.isPresent()) {
            modelMap.addAttribute("usernameErorThis", true);
            return "userAdd";
        }
        if (user.getUsername().getBytes().length <= 3) {
            modelMap.addAttribute("usernameEror", true);
            return "userAdd";
        }
        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            modelMap.addAttribute("passEror", true);
            return "userAdd";
        }
        if (user.getPassword().getBytes().length < 6) {
            modelMap.addAttribute("passErorcharacters", true);
            return "userAdd";
        }
        if (user.getPassword().equals(user.getConfirmPassword()) & user.getUsername().getBytes().length >= 3) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.save(user);
            return "redirect:../users/management";

        }

        return "redirect:../users/add";
    }

    @RequestMapping(value = "/userManagementEdit", method = RequestMethod.POST)
    public String managementUserEdit(@RequestParam("id") String id, @ModelAttribute User user, ModelMap modelMap) {
        modelMap.addAttribute("role", Role.values());
        System.out.println(user.toString());
        if (user.getUsername().getBytes().length <= 3) {
            modelMap.addAttribute("usernameEror", true);
            return "userManagementEdit";
        }
        if (!(user.getPassword().equals(""))) {

            if (!(user.getPassword().equals(user.getConfirmPassword()))) {
                modelMap.addAttribute("passEror", true);
                return "userManagementEdit";
            }
            if (user.getPassword().getBytes().length < 6) {
                modelMap.addAttribute("passErorcharacters", true);
                return "userManagementEdit";
            }
            if (user.getPassword().equals(user.getConfirmPassword()) & user.getUsername().getBytes().length >= 3) {
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                userService.save(user);
                return "redirect:../users/management";
            }
        } else {
            user.setPassword((userService.getUserById(String.valueOf(user.getId()))).getPassword());
            System.out.println(userService.getUserById(String.valueOf(user.getId())).getPassword());
            userService.save(user);
            return "redirect:../users/management";
        }


        return "redirect:../users/add";
    }


}
