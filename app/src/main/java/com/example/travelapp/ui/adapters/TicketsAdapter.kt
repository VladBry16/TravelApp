package com.example.travelapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.models.Ticket
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class TicketsAdapter(private val tickets: List<Ticket>) :
    RecyclerView.Adapter<TicketsAdapter.TicketViewHolder>() {
    inner class TicketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyTextView: TextView = view.findViewById(R.id.company_name_textview)
        val badgeTextView: TextView = view.findViewById(R.id.badge_textview)
        val priceTextView: TextView = view.findViewById(R.id.price_textview)
        val departureTimeTextView: TextView = view.findViewById(R.id.departure_time_textview)
        val departureAirportTextView: TextView = view.findViewById(R.id.departure_airport_textview)
        val arrivalTimeTextView: TextView = view.findViewById(R.id.arrival_time_textview)
        val arrivalAirportTextView: TextView = view.findViewById(R.id.arrival_airport_textview)
        val transferTextView: TextView = view.findViewById(R.id.transfer_textview)
        val durationTextView: TextView = view.findViewById(R.id.duration_textview)


        fun bind(ticket: Ticket) {
            companyTextView.text = ticket.company
            priceTextView.text = formatPrice(ticket.price.value) + " P"
            if (ticket.badge != null) {
                badgeTextView.text = ticket.badge
                badgeTextView.visibility = View.VISIBLE
            }
            else {
                badgeTextView.visibility = View.GONE
            }
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val departureDate = sdf.parse(ticket.departure.date)
            val arrivalDate = sdf.parse(ticket.arrival.date)

            departureTimeTextView.text =  SimpleDateFormat("HH:mm", Locale.getDefault()).format(departureDate!!)
            departureAirportTextView.text = ticket.departure.airport
            arrivalTimeTextView.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(arrivalDate!!)
            arrivalAirportTextView.text = ticket.arrival.airport

            val duration =  arrivalDate.time - departureDate.time
            val hours = TimeUnit.MILLISECONDS.toHours(duration)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60
            durationTextView.text = "${hours}ч ${minutes}м"
            transferTextView.text = if(ticket.hasTransfer) "Есть пересадки" else "Без пересадок"

        }
        private fun formatPrice(price: Int): String {
            val formatted = String.format("%,d", price).replace(',', ' ')
            return formatted
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket, parent, false)
        return TicketViewHolder(view)
    }

    override fun getItemCount(): Int = tickets.size

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(tickets[position])
    }
}