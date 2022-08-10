package com.sorsix.skopjecarrent.service

import com.sorsix.skopjecarrent.model.Car
import com.sorsix.skopjecarrent.model.Reservation
import com.sorsix.skopjecarrent.model.Review
import com.sorsix.skopjecarrent.model.User

interface ReviewService {
    fun addNewReview(reservation: Reservation, car: Car, client: User, rating: Int)
}