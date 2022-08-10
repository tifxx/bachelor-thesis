package com.sorsix.skopjecarrent.api.request

data class CarUpdateRequest(
    val withInsurance: Boolean,
    val withAirCondition: Boolean,
    val priceForADay: Int
)
