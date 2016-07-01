package com.example.workflow;

import java.util.ArrayList;
import javax.persistence.Entity;
import org.springframework.security.core.GrantedAuthority;

public class LoginUser extends org.springframework.security.core.userdetails.User {
    private final User user;
    private final String username;
    private final String password = "";

    public LoginUser(User user) {
        super(user.getUid(), "", true, true, true, true, new ArrayList<GrantedAuthority>());
        this.user = user;
        this.username = user.getUid();
    }

    public User getUser() {
        return user;
    }
}
