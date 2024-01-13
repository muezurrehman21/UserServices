package com.muez.user.services.entities;

public class Rating {
    private String ratingId;
    private String UserId;
    private String hotelId;
    private int rating;
    private String feedback;

    public Rating() {
    }

    public Rating(String ratingId, String userId, String hotelId, int rating, String feedback) {
        this.ratingId = ratingId;
        UserId = userId;
        this.hotelId = hotelId;
        this.rating = rating;
        this.feedback = feedback;
    }
}
