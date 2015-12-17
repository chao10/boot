package com.mode.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mode.dao.UserDAO;
import com.mode.domain.Authority;
import com.mode.domain.User;


@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(final String username) {

        String login = username.toLowerCase();

        User dbUser;
        if (login.contains("@")) {
            dbUser = userDAO.findByEmail(login);
        } else {
            dbUser = userDAO.findByUsername(login);
        }

        if (dbUser == null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        } else if (!dbUser.isActivated()) {
            throw new UserNotActivatedException("User " + login + " is not activated");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : dbUser.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
            grantedAuthorities.add(grantedAuthority);
        }

        return new org.springframework.security.core.userdetails.User(dbUser.getUsername(),
                dbUser.getPassword(), grantedAuthorities);

    }

}