package com.sorsix.skopjecarrent.security

import com.sorsix.skopjecarrent.model.enum.ERole

class JwtResponse(
    val token: String,
    val id: Long,
    val email: String,
    val name: String,
    val surname: String,
    val role: String
) {
    var tokenType = "Bearer"
}
