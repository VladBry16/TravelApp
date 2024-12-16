package com.example.travelapp.models

import com.squareup.moshi.Json

data class Price(
    @Json(name = "value") val value: Int
)