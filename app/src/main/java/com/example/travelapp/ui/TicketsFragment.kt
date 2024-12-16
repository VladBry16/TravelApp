package com.example.travelapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.models.Ticket
import com.example.travelapp.network.TravelApi
import com.example.travelapp.ui.adapters.TicketsAdapter
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class TicketsFragment: Fragment() {

    private val travelApi: TravelApi by inject()
    private lateinit var ticketsRecyclerView: RecyclerView
    private lateinit var fromToTextView: TextView
    private lateinit var datePassengersTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tickets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        ticketsRecyclerView = view.findViewById(R.id.tickets_recyclerview)
        fromToTextView = view.findViewById(R.id.from_to_textview)
        datePassengersTextView = view.findViewById(R.id.date_passengers_textview)
        fromToTextView.text = "Москва - Сочи" // todo get actual data
        datePassengersTextView.text = "23 февраля, 1 пассажир" //todo get actual data
        lifecycleScope.launch {
            loadTickets()
        }
    }

    private suspend fun loadTickets() {
        val url = "https://drive.google.com/uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA"
        val response = travelApi.getTickets(url)
        if(response != null) {
            setupTicketsRecyclerView(response.tickets)
        }
    }
    private fun setupTicketsRecyclerView(tickets: List<Ticket>) {
        ticketsRecyclerView.adapter = TicketsAdapter(tickets)
    }
}