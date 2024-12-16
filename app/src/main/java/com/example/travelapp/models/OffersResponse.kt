package com.example.travelapp.models

import com.squareup.moshi.Json

data class OffersResponse(
    @Json(name = "offers") val offers: List<Offer>
)