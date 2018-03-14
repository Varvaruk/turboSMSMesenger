package com.gmail.v.varvaruk89.services.tsm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    UserService userService;






  @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

      if(userService.findByUsername(s).equals(null)) {
          throw new UsernameNotFoundException("Not found");
      }
        return userService.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("Not found!") );
    }
}
