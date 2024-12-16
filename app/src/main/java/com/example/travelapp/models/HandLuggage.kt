package com.example.travelapp.models

import com.squareup.moshi.Json

data class HandLuggage(
    @Json(name = "has_hand_luggage") val hasHandLuggage: Boolean,
    @Json(name = "size") val size: String? = null
)