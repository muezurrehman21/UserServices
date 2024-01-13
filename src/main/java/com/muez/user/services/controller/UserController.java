package com.muez.user.services.controller;

import com.muez.user.services.entities.User;
import com.muez.user.services.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    /*
    * Crete User
    * */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /*
    * GetUserByUserId
    * */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        User user = userServices.getUser(userId);
        return ResponseEntity.ok(user);
    }

   /*
   * GetAllUsers
   * */
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userServices.getAllUser();
        return ResponseEntity.ok(users);
    }

    /*
    * updateUser
    * */
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updatedUser = userServices.updateUser(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedUser);
    }

    /*
    * DeleteUser
    * */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId){
        userServices.deleteUser(userId);
    }
}
