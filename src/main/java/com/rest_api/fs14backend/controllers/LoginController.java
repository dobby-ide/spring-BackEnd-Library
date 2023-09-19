package com.rest_api.fs14backend.controllers;

import com.rest_api.fs14backend.entities.LoginForm;
import com.rest_api.fs14backend.entities.User;
import com.rest_api.fs14backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@ModelAttribute User user){
        User savedUser = null;
        System.out.println(user.getPassword());
        ResponseEntity response = null;
        if(user.getPassword() != null) {
            try {
                System.out.println(user.getPassword());
                savedUser = userRepository.save(user);

                    response = ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body("Given user details are successfully registered");

            }

         catch(Exception ex){
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception occurred due to " + ex.getMessage());
        } } else{
            response = ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE).body("given user details are not acceptable");
        }
        return response;
    }


    @PostMapping("/userLogin")
    @ResponseBody
    public User getUserDetailsAfterLogin(Authentication authentication){
        System.out.println(authentication);
        System.out.println("inside authentication/login phase path:/userLogin");
        List<User> users = userRepository.findByEmail(authentication.getName());
        if(users.size() > 0){
            System.out.println("authentication successful");
            return users.get(0);

        } else {
            return null;
        }
    }

















}
