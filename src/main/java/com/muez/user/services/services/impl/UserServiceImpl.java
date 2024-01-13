package com.muez.user.services.services.impl;

import com.muez.user.services.entities.User;
import com.muez.user.services.exceptions.ResourceNotFoundException;
import com.muez.user.services.respositories.UserRepository;
import com.muez.user.services.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with given id is not found : " + userId));
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {
        User updateduser = this.getUser(user.getUserId());
        updateduser.setName(user.getName());
        updateduser.setEmail(user.getEmail());
        updateduser.setAbout(user.getAbout());
        return userRepository.save(updateduser);
    }
}
