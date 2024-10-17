package com.tienphuckx.learnspringsec.service.impl;

import com.tienphuckx.learnspringsec.entity.MyUser;
import com.tienphuckx.learnspringsec.repository.UserRepository;
import com.tienphuckx.learnspringsec.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl implements MyUserService {
    @Autowired
    private UserRepository userRepository;

    public MyUser save_new_user(MyUser myUser) {
        return userRepository.save(myUser);
    }
}
