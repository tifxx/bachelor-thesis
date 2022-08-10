package com.sorsix.skopjecarrent.api

import com.sorsix.skopjecarrent.api.request.AddReservationRequest
import com.sorsix.skopjecarrent.api.request.AddReviewRequest
import com.sorsix.skopjecarrent.api.request.ChangeStatusRequest
import com.sorsix.skopjecarrent.model.Reservation
import com.sorsix.skopjecarrent.model.enum.ReservationStatus
import com.sorsix.skopjecarrent.service.*
import com.sorsix.skopjecarrent.service.PDFGenerator.PDFReport
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/reservations")
class ReservationController(val reservationService: ReservationService,
val carService: CarService, val userService: UserService, val locationService: LocationService,
val reviewService: ReviewService){

    @PostMapping("/add")
    fun addNewReservation(@RequestBody addReservation: AddReservationRequest)
    {
        val car=carService.findById(addReservation.carId.toLong())
        val client=userService.findById(addReservation.clientId.toLong())
        val pickUpLocation = locationService.findById(addReservation.pickUpLocationId.toLong())
        val dropOffLocation = locationService.findById(addReservation.dropOffLocationId.toLong())
       // val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        if(car!=null && client!=null) {
            reservationService.saveReservation(
                Reservation(
                    0L, pickUpLocation, dropOffLocation, addReservation.totalPrice,
                    car, client, ReservationStatus.Reserved, LocalDateTime.parse(addReservation.pickUpDate), LocalDateTime.parse(addReservation.dropOffDate), false
                )
            )
        }
    }

    @GetMapping("/all")
    fun allReservations() : List<Reservation> {
        return reservationService.findAll()
    }

    @GetMapping("/byUser")
    fun getReservationsByUser(@RequestParam id: Long) : List<Reservation>
    {
        return reservationService.findByUser(id)
    }

    @GetMapping("/status")
    fun getStatus() : Array<ReservationStatus> {
        return ReservationStatus.values()
    }

    @PutMapping("/changeStatus/{id}")
    fun changeStatus(@PathVariable id: Long, @RequestBody reservationStatus: ChangeStatusRequest) {
        reservationService.changeStatus(id, reservationStatus)
    }

    @PostMapping("/addReview")
    fun addReview(@RequestBody addReviewRequest: AddReviewRequest) {
        val reservation = reservationService.findById(addReviewRequest.reservationId)
        val car = carService.findById(addReviewRequest.carId)
        val client = userService.findById(addReviewRequest.clientId)
        reviewService.addNewReview(reservation, car, client, addReviewRequest.rating)
        reservationService.changeReviewWritten(addReviewRequest.reservationId, true)
        carService.updateRating(addReviewRequest.carId, addReviewRequest.rating)
    }

    @DeleteMapping("/cancel/{id}")
    fun cancelReservation(@PathVariable id: Long)
    {
        reservationService.cancelReservation(id)
    }

    @GetMapping(value = ["/pdf/{id}"], produces = [MediaType.APPLICATION_PDF_VALUE])
    @Throws(
        IOException::class
    )
    fun PDF(@PathVariable id: Long): ResponseEntity<InputStreamResource> {
        val reservation = reservationService.findById(id)
        val bis = PDFReport(reservation as Reservation)
        val headers = HttpHeaders()
        headers.add("Content-Disposition", "inline; filename=skopjecarrent-reservation.pdf")
        return ResponseEntity.ok().headers(headers).
        contentType(MediaType.APPLICATION_PDF)
            .body(InputStreamResource(bis))
    }
}