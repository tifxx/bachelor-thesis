package com.sorsix.skopjecarrent.model.exceptions

data class UserNotFoundException(private val errorMessage: String) : RuntimeException(errorMessage)
