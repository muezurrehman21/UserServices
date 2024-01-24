package com.muez.user.services.services.impl;

import com.muez.user.services.entities.Hotel;
import com.muez.user.services.entities.Rating;
import com.muez.user.services.entities.User;
import com.muez.user.services.exceptions.ResourceNotFoundException;
import com.muez.user.services.respositories.UserRepository;
import com.muez.user.services.services.UserServices;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users =  userRepository.findAll().stream().map(user -> {
            //fetch rating of the above user
            //http://localhost:8083/rating/users/9a0501c3-8b56-43f0-b345-3d1d052659ec
            Rating[] ratingObject =  restTemplate.getForObject("http://RATING-SERVICE/rating/users/" + user.getUserId(), Rating[].class);
//          logger.info("{}", (Object) ratingObject);
            List<Rating> ratings = Arrays.stream(ratingObject).toList();

            List<Rating> ratingList = ratings.stream().map(rating -> {
                //http://localhost:8082/hotels/4b3eaaf6-aef8-4b3a-b5e0-dac3a72e618c
                ResponseEntity<Hotel> forEntitiy = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                Hotel hotel = forEntitiy.getBody();
//            logger.info("response status code: {}", forEntitiy.getStatusCode());
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingList);
            return user;
        }).collect(Collectors.toList());;

        return users;

    }

    @Override
    public User getUser(String userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with given id is not found : " + userId));
        //fetch rating of the above user
        //http://localhost:8083/rating/users/9a0501c3-8b56-43f0-b345-3d1d052659ec
        Rating[] ratingObject =  restTemplate.getForObject("http://RATING-SERVICE/rating/users/" + user.getUserId(), Rating[].class);
//        logger.info("{}", (Object) ratingObject);

        List<Rating> ratings = Arrays.stream(ratingObject).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //http://localhost:8082/hotels/4b3eaaf6-aef8-4b3a-b5e0-dac3a72e618c
            ResponseEntity<Hotel> forEntitiy = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntitiy.getBody();
//            logger.info("response status code: {}", forEntitiy.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
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
