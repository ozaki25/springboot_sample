package com.example.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        User user = null;
        try {
            user = repository.findByUid(uid);
        } catch(Exception e) {
            throw new UsernameNotFoundException("It can not be acquired User");
        }
        if(user == null) throw new UsernameNotFoundException("User not found for login id: " + uid);
        return new LoginUser(user);
    }
}
