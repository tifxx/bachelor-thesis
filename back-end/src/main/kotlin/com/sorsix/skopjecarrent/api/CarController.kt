package com.sorsix.skopjecarrent.api

import com.sorsix.skopjecarrent.api.request.AddCarRequest
import com.sorsix.skopjecarrent.api.request.CarUpdateRequest
import com.sorsix.skopjecarrent.model.Car
import com.sorsix.skopjecarrent.service.CarService
import com.sorsix.skopjecarrent.service.ReservationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cars")
class CarController(val carService: CarService, val reservationService: ReservationService) {

    @PostMapping("/addCar")
    fun addCar(@RequestBody carToAdd: AddCarRequest ) {
        carService.addCar(carToAdd)
    }

    @GetMapping
    fun getCars(): List<Car> {
        return carService.getAllCars()
    }

    @DeleteMapping("/{id}")
    fun deleteCar(@PathVariable id: Long) {
        carService.deleteCar(id)
    }

    @GetMapping("/{id}")
    fun getCarWithId(@PathVariable id: Long): Car {
        return carService.findById(id)
    }

    @GetMapping("/searchAvailable")
    fun searchDates(@RequestParam pickUpDate: String, @RequestParam dropOffDate:String) : List<Car> {
        val allCars=carService.getAllCars()
       return reservationService.checkAvailableCars(allCars, pickUpDate, dropOffDate)
    }

    @PutMapping("/edit/{id}")
    fun editCar(@PathVariable id: Long, @RequestBody postRequest: CarUpdateRequest)
    {
        carService.editCar(id, postRequest)
    }
}