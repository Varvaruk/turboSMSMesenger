package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Role;
import com.gmail.v.varvaruk89.entities.tsm.User;
import com.gmail.v.varvaruk89.repo.tsm.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User userFou = null;
        List<User> userList = (List<User>) userRepository.findAll();
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                userFou = user;
            }

        }
        return Optional.ofNullable(userFou);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = (List<User>) userRepository.findAll();
        return userList;
    }

    @Override
    public void editUserAuthorities(String userId, String role) {
        User user = userRepository.findOne(Long.decode(userId));
        user.setAuthorities(ImmutableList.of(Role.valueOf(role)));

    }

    @Override
    public void editUserPassword(String userId, String pass) {

    }

    @Override
    public void editUserUsername(String userId, String username) {

    }

    @Override
    public void editUserAccountNonLocked(String userId, boolean nonLocked) {

    }

    @Override
    public void editUserAccountNonExpired(String userId, boolean nonExpired) {

    }

    @Override
    public void editUserCredentialsNonExpired(String userId, boolean nonExpired) {

    }

    @Override
    public void editUserEnabled(String userId, boolean enabled) {

    }





    @PostConstruct
    void in() {

        User user = new User();
        user.setUsername("admin");
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setAuthorities(ImmutableList.of(Role.ADMIN));
        save(user);

    }
}