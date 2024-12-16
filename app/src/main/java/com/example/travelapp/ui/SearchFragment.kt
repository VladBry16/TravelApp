package com.example.travelapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.models.TicketsOffer
import com.example.travelapp.network.TravelApi
import com.example.travelapp.ui.adapters.TicketsOffersAdapter
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SearchFragment : Fragment() {

    private val travelApi: TravelApi by inject()
    private lateinit var fromCityTextView: TextView
    private lateinit var toCityTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var economyTextView: TextView
    private lateinit var ticketsOffersRecyclerView: RecyclerView
    private lateinit var showAllTicketsButton: Button
    private var selectedCity: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        fromCityTextView = view.findViewById(R.id.from_city_textview)
        toCityTextView = view.findViewById(R.id.to_city_textview)
        dateTextView = view.findViewById(R.id.date_textview)
        economyTextView = view.findViewById(R.id.economy_textview)
        ticketsOffersRecyclerView = view.findViewById(R.id.tickets_offers_recyclerview)
        showAllTicketsButton = view.findViewById(R.id.show_all_tickets_button)

        selectedCity = arguments?.getString("selectedCity")

        toCityTextView.text = selectedCity
        showAllTicketsButton.setOnClickListener {
            findNavController().navigate(R.id.menu_profile) // todo: navigate to proper tickets screen
            findNavController().navigate(R.id.menu_tickets)
        }
        ticketsOffersRecyclerView.layoutManager = LinearLayoutManager(context)
        lifecycleScope.launch {
            loadTicketsOffers()
        }
    }
    private suspend fun loadTicketsOffers() {
        val url = "https://drive.usercontent.google.com/u/0/uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download"
        val response =  try {
            val response = travelApi.getTicketsOffers(url)
            Log.d("SearchFragment", "response json: $response")
            response
        } catch(e: Exception){
            Log.e("SearchFragment", "response error: $e")
            null
        }
        if(response?.ticketsOffers != null) {
            setupTicketsOffersRecyclerView(response.ticketsOffers)
        }
    }
    private fun setupTicketsOffersRecyclerView(ticketsOffers: List<TicketsOffer>) {
        Log.d("SearchFragment", "setupTicketsOffersRecyclerView: ${ticketsOffers.size}")
        ticketsOffersRecyclerView.adapter = TicketsOffersAdapter(ticketsOffers)
    }
}