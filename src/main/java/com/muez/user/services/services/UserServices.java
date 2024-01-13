package com.muez.user.services.services;

import com.muez.user.services.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServices {

    //create
    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    void deleteUser(String userId);

    User updateUser(User user);



}
