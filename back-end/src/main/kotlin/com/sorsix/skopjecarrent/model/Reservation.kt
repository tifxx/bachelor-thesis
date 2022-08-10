package com.sorsix.skopjecarrent.model

import com.sorsix.skopjecarrent.model.enum.ReservationStatus
import javax.persistence.*
import java.time.LocalDateTime

@Entity
data class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    val pickUpLocation: Location,
    @ManyToOne
    val dropOffLocation: Location,
    val totalPrice: Int,
    @OneToOne
    val car: Car,
    @ManyToOne
    val client: User,
    @Enumerated(value = EnumType.STRING)
    val status: ReservationStatus,
    val pickUpDate: LocalDateTime,
    val dropOffDate: LocalDateTime,
    val reviewWritten: Boolean
)