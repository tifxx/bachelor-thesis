package com.sorsix.skopjecarrent.service

import com.sorsix.skopjecarrent.api.request.AddLocationRequest
import com.sorsix.skopjecarrent.model.Location

interface LocationService {
    fun findById(id: Long): Location
    fun findAllLocations() : List<Location>
    fun addNewLocation(location: AddLocationRequest) : Location
}