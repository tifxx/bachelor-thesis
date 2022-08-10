package com.sorsix.skopjecarrent.model.exceptions

class LocationNotFoundException(private val errorMessage: String) : RuntimeException(errorMessage) {
}