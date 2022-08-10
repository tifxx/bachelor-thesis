package com.sorsix.skopjecarrent.service

import com.sorsix.skopjecarrent.api.request.AddCarRequest
import com.sorsix.skopjecarrent.api.request.CarUpdateRequest
import com.sorsix.skopjecarrent.model.Car
import com.sorsix.skopjecarrent.model.Review
import com.sorsix.skopjecarrent.model.enum.Seats
import com.sorsix.skopjecarrent.model.enum.Transmission
import java.util.*
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToMany

interface CarService {
    fun findById(id: Long): Car
    fun addCar(carToAdd: AddCarRequest): Car
    fun deleteCar(id: Long)
    fun getAllCars(): List<Car>
    fun findCarsByTransmission(transmission: Transmission): List<Car>
    fun findCarsBySeats(seats: Seats): List<Car>
    fun findCarsWithInsurance(withInsurance: Boolean): List<Car>
    fun findCarsByTransmissionAndSeats(transmission: Transmission, seats: Seats): List<Car>
    fun findCarsWithAirConditioner(withAirConditioner: Boolean): List<Car>
    fun editCar(id: Long, postRequest: CarUpdateRequest)
    fun updateRating(id: Long, rating: Int)
}