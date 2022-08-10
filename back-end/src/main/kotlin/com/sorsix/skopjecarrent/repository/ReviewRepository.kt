package com.sorsix.skopjecarrent.repository

import com.sorsix.skopjecarrent.model.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : JpaRepository<Review, Long> {
}