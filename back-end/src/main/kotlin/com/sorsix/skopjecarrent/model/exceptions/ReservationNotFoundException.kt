package com.sorsix.skopjecarrent.model.exceptions

class ReservationNotFoundException(private val errorMessage: String) : RuntimeException(errorMessage) {
}