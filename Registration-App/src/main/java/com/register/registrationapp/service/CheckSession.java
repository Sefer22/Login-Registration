package com.register.registrationapp.service;

import com.register.registrationapp.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class CheckSession {

    public User sessionCheck(HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute("istifadeci");
        if(user==null) {
            return null;
        }
        return user;
    }
}
