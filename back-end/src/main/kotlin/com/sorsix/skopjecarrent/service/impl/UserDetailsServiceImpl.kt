package com.sorsix.skopjecarrent.service.impl

import com.sorsix.skopjecarrent.model.User
import com.sorsix.skopjecarrent.model.exceptions.UserNotFoundException
import com.sorsix.skopjecarrent.repository.UserRepository
import com.sorsix.skopjecarrent.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl(val repository: UserRepository) : UserDetailsService, UserService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User? = repository.findByEmail(username)
            ?.orElseThrow { UserNotFoundException("User Not Found with username: $username") }
        return User.build(user!!)
    }

    override fun findById(id: Long): User {
        return repository.findByIdOrNull(id) ?: throw UsernameNotFoundException("User with id $id not found")
    }

}
