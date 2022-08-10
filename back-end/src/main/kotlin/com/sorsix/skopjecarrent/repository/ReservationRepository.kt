package com.sorsix.skopjecarrent.repository

import com.sorsix.skopjecarrent.model.Car
import com.sorsix.skopjecarrent.model.Reservation
import com.sorsix.skopjecarrent.model.enum.ReservationStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface ReservationRepository : JpaRepository<Reservation, Long>{
    @Modifying
    @Transactional
    @Query("update Reservation r set r.status = :status where r.id = :id")
    fun updateStatus(id: Long, status: ReservationStatus)

    @Modifying
    @Transactional
    @Query("update Reservation r set r.reviewWritten = :reviewWritten where r.id = :id")
    fun updateReviewWritten(id: Long, reviewWritten: Boolean)

}