package com.sorsix.skopjecarrent.api.request

import com.sorsix.skopjecarrent.model.enum.Seats
import com.sorsix.skopjecarrent.model.enum.Transmission

data class AddLocationRequest(
    val name: String,
    val longitude: String,
    val latitude: String
)