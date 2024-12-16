package com.example.travelapp.models

import com.squareup.moshi.Json

data class TicketsResponse(
    @Json(name = "tickets") val tickets: List<Ticket>
)