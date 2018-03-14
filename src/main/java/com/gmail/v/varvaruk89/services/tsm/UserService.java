package com.gmail.v.varvaruk89.services.tsm;

import com.gmail.v.varvaruk89.entities.tsm.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

   void save(User user);
   void delete(User user);
   Optional<User> findByUsername(String username);

}
