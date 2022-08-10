package com.sorsix.skopjecarrent.service.impl

import com.sorsix.skopjecarrent.api.request.ChangeStatusRequest
import com.sorsix.skopjecarrent.model.Car
import com.sorsix.skopjecarrent.model.Location
import com.sorsix.skopjecarrent.model.Reservation
import com.sorsix.skopjecarrent.model.User
import com.sorsix.skopjecarrent.model.enum.ReservationStatus
import com.sorsix.skopjecarrent.model.exceptions.CarNotFoundException
import com.sorsix.skopjecarrent.model.exceptions.ReservationNotFoundException
import com.sorsix.skopjecarrent.repository.ReservationRepository
import com.sorsix.skopjecarrent.service.ReservationService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Service
data class ReservationServiceImpl(val repository: ReservationRepository) : ReservationService {
    override fun findAll(): List<Reservation> {
        return repository.findAll()
    }

    override fun findById(id: Long): Reservation {
        return repository.findByIdOrNull(id) ?: throw ReservationNotFoundException("Reservation with id $id not found.")

    }

    override fun saveReservation(reservation: Reservation) {
        repository.save(reservation)
    }

    override fun findByUser(id: Long): List<Reservation> {
        val all=repository.findAll()
        val final= mutableListOf<Reservation>()
        for (reservation in all)
        {
            if(reservation.client.id==id)
                final.add(reservation)
        }
        return final
    }

    override fun checkAvailableCars(allCars: List<Car>, pickUpDate: String, dropOffDate: String): List<Car> {
        val carsRes= mutableListOf<Car>()
        val allReservations=repository.findAll()
        val pickUp= LocalDateTime.parse(pickUpDate)
        val dropOff= LocalDateTime.parse(dropOffDate)
        for(car in allCars)
        {
            var total=0
            for(reservation in allReservations)
            {
                if(car==reservation.car)
                {
                    total++
                    if((reservation.pickUpDate.isBefore(pickUp)&&reservation.dropOffDate.isBefore(pickUp))||
                        (reservation.pickUpDate.isAfter(dropOff)))
                    {
                        if(!carsRes.contains(car))
                            carsRes.add(car)
                    }
                    else {
                        if(carsRes.contains(car))
                            carsRes.remove(car)
                        break
                    }
                }
            }
            if(total==0)
                carsRes.add(car)
        }
        return carsRes.distinct()
    }

    override fun changeStatus(id: Long, reservationStatus: ChangeStatusRequest) {
        repository.updateStatus(id, reservationStatus.reservationStatus)
    }

    override fun changeReviewWritten(id: Long, reviewWritten: Boolean) {
        repository.updateReviewWritten(id, reviewWritten)
    }

    override fun cancelReservation(id: Long) {
        val reservation=repository.findByIdOrNull(id)
        if(reservation!=null)
            repository.delete(reservation)
    }


}