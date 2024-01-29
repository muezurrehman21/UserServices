package com.muez.user.services.external.service;

import com.muez.user.services.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

    @GetMapping("rating/users/{userId}")
    List<Rating> getRating(@PathVariable String userId);
}
