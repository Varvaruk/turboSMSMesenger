package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.Role;
import com.gmail.v.varvaruk89.entities.tsm.User;
import com.gmail.v.varvaruk89.repo.tsm.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service

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
    public User getUserById(String Id) {
        return userRepository.findOne(Long.decode(Id));
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
        userRepository.save(user);

    }

    @Override
    public void editUserPassword(String userId, String pass) {
        User user = userRepository.findOne(Long.decode(userId));
        user.setPassword(new BCryptPasswordEncoder().encode(pass));
        userRepository.save(user);
    }

    @Override
    public void editUserUsername(String userId, String username) {
        User user = userRepository.findOne(Long.decode(userId));
        user.setUsername(username);
        userRepository.save(user);

    }

    @Override
    public void editUserAccountNonLocked(String userId, boolean nonLocked) {
        User user = userRepository.findOne(Long.decode(userId));
        user.setAccountNonLocked(nonLocked);
        userRepository.save(user);
    }

    @Override
    public void editUserAccountNonExpired(String userId, boolean nonExpired) {
        User user = userRepository.findOne(Long.decode(userId));
        user.setAccountNonExpired(nonExpired);
        userRepository.save(user);
    }

    @Override
    public void editUserCredentialsNonExpired(String userId, boolean nonExpired) {
        User user = userRepository.findOne(Long.decode(userId));
        user.setCredentialsNonExpired(nonExpired);
        userRepository.save(user);
    }

    @Override
    public void editUserEnabled(String userId, boolean enabled) {
        User user = userRepository.findOne(Long.decode(userId));
        user.setEnabled(enabled);
        userRepository.save(user);
    }


    @PostConstruct
    void in() {

        User user;
        if(!(findByUsername("admin").isPresent())) {
            user = new User();
            user.setUsername("admin");
        }else{
            user = findByUsername("admin").get();
        }

        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setAuthorities(ImmutableList.of(Role.ADMIN));
        save(user);

    }
}