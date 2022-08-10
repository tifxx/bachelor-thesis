package com.sorsix.skopjecarrent.service.impl

import com.sorsix.skopjecarrent.model.Car
import com.sorsix.skopjecarrent.model.Reservation
import com.sorsix.skopjecarrent.model.Review
import com.sorsix.skopjecarrent.model.User
import com.sorsix.skopjecarrent.repository.ReviewRepository
import com.sorsix.skopjecarrent.service.ReviewService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReviewServiceImpl(val repository: ReviewRepository) : ReviewService{
    override fun addNewReview(reservation: Reservation, car: Car, client: User, rating: Int) {
        repository.save(Review(0L, rating, car, client, reservation ))
    }
}