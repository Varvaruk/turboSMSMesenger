package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    void save(User user);

    void delete(User user);

    Optional<User> findByUsername(String username);

    List<User> getAllUsers();

    void editUserAuthorities(String userId,String role);

    void editUserPassword(String userId, String pass);

    void editUserUsername(String userId, String username);

    void editUserAccountNonLocked(String userId, boolean nonLocked);

    void editUserAccountNonExpired(String userId,boolean nonExpired);

    void editUserCredentialsNonExpired(String userId,boolean nonExpired );

    void editUserEnabled(String userId,boolean enabled);
}
