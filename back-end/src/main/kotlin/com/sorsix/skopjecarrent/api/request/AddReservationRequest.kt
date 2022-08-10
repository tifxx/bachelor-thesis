package com.sorsix.skopjecarrent.api.request

import org.springframework.web.bind.annotation.RequestParam

data class AddReservationRequest(
    val pickUpLocationId: String,
    val dropOffLocationId: String,
    val totalPrice: Int,
    val carId: Int,
    val clientId: Int,
    val pickUpDate: String,
    val dropOffDate: String,
    val reviewWritten: Boolean
)
