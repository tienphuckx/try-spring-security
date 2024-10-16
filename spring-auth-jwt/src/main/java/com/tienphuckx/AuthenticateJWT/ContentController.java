package com.tienphuckx.AuthenticateJWT;

import com.tienphuckx.AuthenticateJWT.jwt.JwtService;
import com.tienphuckx.AuthenticateJWT.model.LoginRequestForm;
import com.tienphuckx.AuthenticateJWT.model.MyUser;
import com.tienphuckx.AuthenticateJWT.model.MyUserDetailService;
import com.tienphuckx.AuthenticateJWT.model.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;

    @GetMapping("/")
    public String index() {
        return "Welcome to index page";
    }

    @GetMapping("/home")
    public String publicPage() {
        return "Welcome public home page";  // Corresponds to public-page.html
    }

    @GetMapping("/user/home")
    public String userPage() {
        return "Welcome to User home page";  // Corresponds to welcome-user.html
    }

    @GetMapping("/admin/home")
    public String adminPage() {
        return "Welcome to Admin home page";  // Corresponds to welcome-admin.html
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginRequestForm loginRequestForm) {
         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestForm.username(), loginRequestForm.password()
            ));
         if(authentication.isAuthenticated()) {
             return jwtService.generateToken(myUserDetailService
                     .loadUserByUsername(loginRequestForm.username()));
         }else{
             throw new UsernameNotFoundException("Invalid username or password");
         }
    }
}
