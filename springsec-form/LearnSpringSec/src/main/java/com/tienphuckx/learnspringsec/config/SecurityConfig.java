package com.tienphuckx.learnspringsec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for development; enable in production.
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/login", "/pub", "/register/**").permitAll();  // Allow access to login and public pages
                    registry.requestMatchers("/user/**").hasRole("USER");  // Restrict access to USER pages
                    registry.requestMatchers("/admin/**").hasRole("ADMIN");  // Restrict access to ADMIN pages
                    registry.anyRequest().authenticated();  // All other requests require authentication
                })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/perform_login")  // The custom login page//
                            .usernameParameter("username")  // The name attribute of the username input field
                            .passwordParameter("pwd") //                         .loginProcessingUrl("/perform_login")  // URL to submit the login form
                            .successHandler(new AuthenSuccessHandle())//.defaultSuccessUrl("/user", true)  // Redirect to the user page after successful login
                            .failureUrl("/login?error=true")  // Redirect back to the login page on failure
                            .permitAll();
                })
                .logout(httpSecurityLogoutConfigurer -> {
                    httpSecurityLogoutConfigurer
                            .logoutUrl("/perform_logout")
                            .logoutSuccessUrl("/login?logout=true")
                            .deleteCookies("JSESSIONID")
                            .permitAll();
                })
                .build();
    }


//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails normalUser = User.builder()
//                .username("user")
//                .password("$2a$12$Os9hsoZ5uIodBEw2kN2ogu7x.XFmvgmA27U6CFTTm4DF.brp2nKI6")
//                .roles("USER")
//                .build();
//        UserDetails adminUser = User.builder()
//                .username("admin")
//                .password("$2a$12$OdxIJpRkn37YLkkaexAb6elL5kYuVe9NYi7jL4bnZmYNDOK4vkiNq")
//                .roles("ADMIN","USER")
//                .build();
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }

    @Bean
    UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
