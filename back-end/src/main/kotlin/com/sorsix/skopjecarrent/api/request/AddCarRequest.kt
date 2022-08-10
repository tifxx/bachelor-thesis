package com.sorsix.skopjecarrent.api.request

import com.sorsix.skopjecarrent.model.enum.Seats
import com.sorsix.skopjecarrent.model.enum.Transmission
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class AddCarRequest(
    val model: String,
    val priceForADay: Int,
    val image: String,
    val withInsurance: Boolean,
    val withAirConditioner: Boolean,
    val seats: Seats,
    val transmission: Transmission,
) {
}