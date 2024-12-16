package com.example.travelapp.models

import com.squareup.moshi.Json

data class Offer(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "town") val town: String,
    @Json(name = "price") val price: Price
)