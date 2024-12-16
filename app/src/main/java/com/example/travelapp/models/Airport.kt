package com.example.travelapp.models

import com.squareup.moshi.Json

data class Airport(
    @Json(name = "town") val town: String,
    @Json(name = "date") val date: String,
    @Json(name = "airport") val airport: String
)