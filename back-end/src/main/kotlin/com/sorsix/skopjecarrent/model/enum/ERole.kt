package com.sorsix.skopjecarrent.model.enum

import org.springframework.security.core.GrantedAuthority

enum class ERole : GrantedAuthority {
    USER,
    ADMIN;
    override fun getAuthority(): String = name
}
