package com.sorsix.skopjecarrent.service.impl

import com.sorsix.skopjecarrent.api.request.AddCarRequest
import com.sorsix.skopjecarrent.api.request.CarUpdateRequest
import com.sorsix.skopjecarrent.model.Car
import com.sorsix.skopjecarrent.model.enum.Seats
import com.sorsix.skopjecarrent.model.enum.Transmission
import com.sorsix.skopjecarrent.model.exceptions.CarNotFoundException
//import com.sorsix.skopjecarrent.model.exceptions.CarNotFoundException
import com.sorsix.skopjecarrent.repository.CarRepository
import com.sorsix.skopjecarrent.repository.ReservationRepository
import com.sorsix.skopjecarrent.service.CarService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CarServiceImpl(val carRepository: CarRepository, val reservationRepository: ReservationRepository) : CarService {
    override fun findById(id: Long): Car {
        return carRepository.findByIdOrNull(id) ?: throw CarNotFoundException("Car with id $id not found.")
    }

    override fun addCar(carToAdd: AddCarRequest): Car {
        return carRepository.save(
            Car(
                0,
                carToAdd.model,
                carToAdd.priceForADay,
                carToAdd.image,
                carToAdd.withInsurance,
                carToAdd.withAirConditioner,
                carToAdd.seats,
                carToAdd.transmission,
                0,
                0
            )
        )
    }

    override fun deleteCar(id: Long) {
        val car = carRepository.findByIdOrNull(id) ?: throw CarNotFoundException("Car with id $id not found.")
        carRepository.delete(car)
    }

    override fun getAllCars(): List<Car> {
        return carRepository.findAll()
    }

    override fun findCarsByTransmission(transmission: Transmission): List<Car> {
        return carRepository.findByTransmission(transmission)
    }

    override fun findCarsBySeats(seats: Seats): List<Car> {
        return carRepository.findBySeats(seats)
    }

    override fun findCarsWithInsurance(withInsurance: Boolean): List<Car> {
        return carRepository.findByWithInsurance(withInsurance)
    }

    override fun findCarsByTransmissionAndSeats(transmission: Transmission, seats: Seats): List<Car> {
        return carRepository.findByTransmissionAndSeats(transmission, seats)
    }

    override fun findCarsWithAirConditioner(withAirConditioner: Boolean): List<Car> {
        return carRepository.findByWithAirConditioner(withAirConditioner)
    }

    override fun editCar(id: Long, postRequest: CarUpdateRequest){
        this.carRepository.update(id, postRequest.priceForADay, postRequest.withAirCondition, postRequest.withInsurance)
    }

    override fun updateRating(id: Long, rating: Int) {
        this.carRepository.updateRating(id, rating)
    }


}