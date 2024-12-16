package com.example.travelapp.models

import com.google.gson.annotations.SerializedName

data class TicketsOffer(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("time_range") val timeRange: List<String>,
    @SerializedName("price") val price: Price
)