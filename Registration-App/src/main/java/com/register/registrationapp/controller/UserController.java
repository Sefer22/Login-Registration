package com.register.registrationapp.controller;

import com.register.registrationapp.entity.User;
import com.register.registrationapp.service.CheckSession;
import com.register.registrationapp.service.EmailChecking;
import com.register.registrationapp.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EmailChecking emailChecking;

    @Autowired
    private CheckSession checkSession;

    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping("/register")
    public String register(User user) {
        Optional<User> user1 = emailChecking.checkEmail(user);
        if(user1.isPresent()) {
            return "redirect:register?same";
        }
        userService.save(user);
        return "redirect:register?success";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String loginToWebsite(@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest httpServletRequest) {
       Optional<User> user =  userService.login(email, password);
       if(!user.isPresent()) {
           model.addAttribute("Error","There is not such a user");
            return "login";
       }
       httpServletRequest.getSession().setAttribute("istifadeci",user.get());
        return "welcome";
    }

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest httpServletRequest) {
     User user = checkSession.sessionCheck(httpServletRequest);
       if(user==null) {
           return "login";
       }
        return "welcome";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest,Model model) {
        httpServletRequest.getSession().invalidate();
        model.addAttribute("logout","siz ugurla sistemnen cixis etdiniz");
        return "login";
    }
}
