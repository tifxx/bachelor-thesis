package com.sorsix.skopjecarrent.service

import com.sorsix.skopjecarrent.api.request.ChangeStatusRequest
import com.sorsix.skopjecarrent.model.Car
import com.sorsix.skopjecarrent.model.Location
import com.sorsix.skopjecarrent.model.Reservation
import com.sorsix.skopjecarrent.model.User
import com.sorsix.skopjecarrent.model.enum.ReservationStatus
import java.time.LocalDate
import java.time.chrono.ChronoLocalDateTime
import java.util.*

interface ReservationService {
    fun findAll() : List<Reservation>
    fun findById(id: Long) : Reservation
    fun saveReservation(reservation: Reservation)
    fun findByUser(id: Long) : List<Reservation>
    fun checkAvailableCars(allCars: List<Car>, pickUpDate: String, dropOffDate: String) : List<Car>
    fun changeStatus(id: Long, reservationStatus: ChangeStatusRequest)
    fun changeReviewWritten(id: Long, reviewWritten: Boolean)
    fun cancelReservation(id: Long)
}