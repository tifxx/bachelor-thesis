package com.sorsix.skopjecarrent.api.request

import com.sorsix.skopjecarrent.model.enum.ReservationStatus

data class ChangeStatusRequest(
    val reservationStatus: ReservationStatus
)
