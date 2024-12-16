package com.example.travelapp.network

import com.example.travelapp.models.OffersResponse
import com.example.travelapp.models.TicketsOffersResponse
import com.example.travelapp.models.TicketsResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface TravelApi {

    @GET
    suspend fun getOffers(@Url url: String): OffersResponse

    @GET
    suspend fun getTicketsOffers(@Url url: String): TicketsOffersResponse

    @GET
    suspend fun getTickets(@Url url: String): TicketsResponse
}