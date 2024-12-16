package com.example.travelapp.models

import com.google.gson.annotations.SerializedName

data class TicketsOffersResponse(
    @SerializedName("tickets_offers") val ticketsOffers: List<TicketsOffer>
)