package com.sorsix.skopjecarrent.api

import com.sorsix.skopjecarrent.api.request.AddCarRequest
import com.sorsix.skopjecarrent.api.request.AddLocationRequest
import com.sorsix.skopjecarrent.model.Car
import com.sorsix.skopjecarrent.model.Location
import com.sorsix.skopjecarrent.service.LocationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/locations")
class LocationController(val locationService: LocationService) {

    @GetMapping
    fun getLocations(): List<Location> {
        return locationService.findAllLocations()
    }

    @PostMapping("/addLocation")
    fun addNewLocation(@RequestBody locationToAdd: AddLocationRequest) {
        locationService.addNewLocation(locationToAdd)
    }
}