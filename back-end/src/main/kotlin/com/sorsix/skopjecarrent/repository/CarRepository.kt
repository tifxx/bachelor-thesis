package com.sorsix.skopjecarrent.repository

import com.sorsix.skopjecarrent.model.Car
import com.sorsix.skopjecarrent.model.enum.Seats
import com.sorsix.skopjecarrent.model.enum.Transmission
import org.hibernate.sql.Update
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface CarRepository : JpaRepository<Car, Long> {
    fun findByTransmission(transmission: Transmission): List<Car>
    fun findBySeats(seats: Seats): List<Car>
    fun findByTransmissionAndSeats(transmission: Transmission, seats: Seats): List<Car>
    fun findByWithInsurance(withInsurance: Boolean): List<Car>
    fun findByWithAirConditioner(withAirConditioner: Boolean): List<Car>
    @Modifying
    @Transactional
    @Query("update Car c set c.priceForADay = :priceForADay, c.withInsurance = :withInsurance, c.withAirConditioner = :withAirConditioner where c.id = :id")
    fun update(id: Long, priceForADay: Int, withAirConditioner: Boolean, withInsurance: Boolean)
    @Modifying
    @Transactional
    @Query("update Car c set c.sumRating = c.sumRating + :rating, c.numReviews = c.numReviews + 1 WHERE c.id = :id")
    fun updateRating(id: Long, rating: Int)
}