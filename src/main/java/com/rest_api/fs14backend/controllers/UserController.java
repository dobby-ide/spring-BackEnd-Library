package com.rest_api.fs14backend.controllers;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import com.rest_api.fs14backend.model.UserDTO;
import com.rest_api.fs14backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/{userId}/return-book/{bookId}")
    public ResponseEntity returnBook(@PathVariable("userId") UUID userId,@PathVariable ("bookId") UUID bookId){
        try{
            userService.returnBook(userId, bookId);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/{userId}/borrow-book/{bookId}")
    public ResponseEntity borrowBook( @PathVariable("userId") UUID userId,@PathVariable("bookId") UUID bookId) {
        try {

            userService.borrowBook(userId, bookId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public List<UserDTO> listUsers(@RequestParam(required=false) String userName){
        return userService.listUsers(userName);
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable("userId") UUID userId){
        return userService.getUserById(userId).orElseThrow();
    }

    @PostMapping
    public ResponseEntity handlePost(@Validated @ModelAttribute UserDTO user){
        UserDTO savedUser = userService.saveNewUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/users/" + savedUser.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity updateById(@PathVariable("userId") UUID userId, @RequestBody UserDTO user){

        if(userService.updateUserById(userId,user).isEmpty()){
            throw new NotFoundException("the user cannot be found");
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteById(@PathVariable UUID userId){
        if(!userService.deleteById(userId)){
            throw new NotFoundException("user cant be found");
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
