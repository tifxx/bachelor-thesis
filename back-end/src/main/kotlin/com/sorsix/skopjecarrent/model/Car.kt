package com.sorsix.skopjecarrent.model

import com.sorsix.skopjecarrent.model.enum.Seats
import com.sorsix.skopjecarrent.model.enum.Transmission
import javax.persistence.*

@Entity
data class Car(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val model: String,
    val priceForADay: Int,
    val image: String,
    val withInsurance: Boolean,
    val withAirConditioner: Boolean,
    @Enumerated(value = EnumType.STRING)
    val seats: Seats,
    @Enumerated(value = EnumType.STRING)
    val transmission: Transmission,
    val sumRating: Int,
    val numReviews: Int
)
