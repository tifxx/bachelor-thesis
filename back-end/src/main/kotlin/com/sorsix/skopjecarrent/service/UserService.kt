package com.sorsix.skopjecarrent.service

import com.sorsix.skopjecarrent.model.User

interface UserService {
    fun findById(id: Long): User
}