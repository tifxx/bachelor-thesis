package com.sorsix.skopjecarrent.security

data class LoginRequest(
    var email: String? = null,
    var password: String? = null
)
