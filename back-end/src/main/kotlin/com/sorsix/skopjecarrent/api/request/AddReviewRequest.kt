package com.sorsix.skopjecarrent.api.request

import java.time.LocalDateTime

data class AddReviewRequest(
    val reservationId: Long,
    val carId: Long,
    val clientId: Long,
    val rating: Int
)
