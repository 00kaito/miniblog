package com.akademiakodu.blog.demo.controller;

import com.akademiakodu.blog.demo.model.entities.User;
import com.akademiakodu.blog.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.rmi.MarshalledObject;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String postRegisterForm(@RequestParam String username, @RequestParam String password, Model model) {
        User regUser = new User();
        regUser.setUserName(username);
        regUser.setUserPassword(password);
        userRepository.save(regUser);
        return "register";
    }
}
