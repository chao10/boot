package com.mode.api;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mode.dao.UserDAO;
import com.mode.domain.User;

/**
 * Created by chao on 11/22/15.
 */
@RestController
public class MainAPI {

    @Autowired
    private UserDAO userDAO;
    @RequestMapping("/{login}")
    public User home(@PathVariable String login) {
        if (login.contains("@")) {
            return userDAO.findByEmail(login);
        } else {
            return userDAO.findByUsername(login);
        }
    }

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(Principal user) {
        return new Greeting(counter.incrementAndGet(), String.format(template, user.toString()));
    }

    public class Greeting {

        private final long id;

        private final String content;

        public long getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public Greeting(long id, String content) {
            this.id = id;
            this.content = content;
        }

    }
}