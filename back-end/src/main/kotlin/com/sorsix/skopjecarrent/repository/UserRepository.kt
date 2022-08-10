package com.sorsix.skopjecarrent.repository

import com.sorsix.skopjecarrent.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>
    fun existsByEmail(email: String): Boolean?

    @Modifying
    @Transactional
    @Query("update User u set u.email = :email where u.id = :userId")
    fun updateEmail(userId: Long, email: String)

    @Modifying
    @Transactional
    @Query("update User u set u.name = :name where u.id = :userId")
    fun updateName(userId: Long, name: String)

    @Modifying
    @Transactional
    @Query("update User u set u.surname = :surname where u.id = :userId")
    fun updateSurname(userId: Long, surname: String)

    @Modifying
    @Transactional
    @Query("update User u set u.password = :password where u.id = :userId")
    fun updatePassword(userId: Long, password: String)

}
