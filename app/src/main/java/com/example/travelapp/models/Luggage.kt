package com.example.travelapp.models

import com.squareup.moshi.Json

data class Luggage(
    @Json(name = "has_luggage") val hasLuggage: Boolean,
    @Json(name = "price") val price: Price? = null,
)