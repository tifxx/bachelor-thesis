package com.sorsix.skopjecarrent.model.exceptions

class CarNotFoundException(private val errorMessage: String) : RuntimeException(errorMessage) {
}