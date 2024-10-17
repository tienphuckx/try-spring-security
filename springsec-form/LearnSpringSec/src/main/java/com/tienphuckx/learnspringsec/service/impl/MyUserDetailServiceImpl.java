package com.tienphuckx.learnspringsec.service.impl;

import com.tienphuckx.learnspringsec.entity.MyUser;
import com.tienphuckx.learnspringsec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByUsername(username);
        var userObj = user.get();
        if(user.isPresent()) {
            return User.builder().username(userObj.getUsername())
                    .password(userObj.getPwd())
                    .roles(userObj.getEachRoles())
                    .build();
        }else{
            throw new UsernameNotFoundException(username);
        }
    }
}
