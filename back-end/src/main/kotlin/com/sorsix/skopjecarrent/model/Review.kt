package com.sorsix.skopjecarrent.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val rating: Int,
    @ManyToOne
    val car: Car,
    @ManyToOne
    val client: User,
    @OneToOne
    val reservation: Reservation
)
