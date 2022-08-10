package com.sorsix.skopjecarrent.service.impl

import com.sorsix.skopjecarrent.api.request.AddLocationRequest
import com.sorsix.skopjecarrent.model.Location
import com.sorsix.skopjecarrent.model.exceptions.CarNotFoundException
import com.sorsix.skopjecarrent.model.exceptions.LocationNotFoundException
import com.sorsix.skopjecarrent.repository.LocationRepository
import com.sorsix.skopjecarrent.service.LocationService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LocationServiceImpl(val locationRepository: LocationRepository) : LocationService {
    override fun findById(id: Long): Location {
        return locationRepository.findByIdOrNull(id) ?: throw LocationNotFoundException("Location with id $id not found.")
    }

    override fun findAllLocations(): List<Location> {
        return locationRepository.findAll()
    }

    override fun addNewLocation(location: AddLocationRequest): Location {
        return locationRepository.save(Location(0,location.name, location.longitude, location.latitude))
    }


}