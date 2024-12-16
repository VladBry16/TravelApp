package com.example.travelapp.models

import com.squareup.moshi.Json

data class Ticket(
    @Json(name = "id") val id: Int,
    @Json(name = "badge") val badge: String? = null,
    @Json(name = "price") val price: Price,
    @Json(name = "provider_name") val providerName: String,
    @Json(name = "company") val company: String,
    @Json(name = "departure") val departure: Airport,
    @Json(name = "arrival") val arrival: Airport,
    @Json(name = "has_transfer") val hasTransfer: Boolean,
    @Json(name = "has_visa_transfer") val hasVisaTransfer: Boolean,
    @Json(name = "luggage") val luggage: Luggage,
    @Json(name = "hand_luggage") val handLuggage: HandLuggage,
    @Json(name = "is_returnable") val isReturnable: Boolean,
    @Json(name = "is_exchangable") val isExchangable: Boolean
)