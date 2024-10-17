package com.tienphuckx.learnspringsec.controller;

import com.tienphuckx.learnspringsec.entity.MyUser;
import com.tienphuckx.learnspringsec.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public MyUser registerUser(@RequestBody MyUser user) {
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        return myUserService.save_new_user(user);
    }
}
