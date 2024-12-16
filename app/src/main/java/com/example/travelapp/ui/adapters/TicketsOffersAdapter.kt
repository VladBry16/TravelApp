package com.example.travelapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.models.TicketsOffer

class TicketsOffersAdapter(private val ticketsOffers: List<TicketsOffer>) :
    RecyclerView.Adapter<TicketsOffersAdapter.TicketsOfferHolder>() {
    inner class TicketsOfferHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyNameTextView: TextView = view.findViewById(R.id.company_name_textview)
        val timeRangeTextView: TextView = view.findViewById(R.id.time_range_textview)
        val priceTextView: TextView = view.findViewById(R.id.price_textview)
        val companyLogo: ImageView = view.findViewById(R.id.company_imageview)

        fun bind(ticketOffer: TicketsOffer) {
            Log.d("TicketsOffersAdapter", "Binding: ${ticketOffer.title}, ${ticketOffer.timeRange}, ${ticketOffer.price.value}")
            companyNameTextView.text = ticketOffer.title
            timeRangeTextView.text = ticketOffer.timeRange.joinToString(" ")
            priceTextView.text = formatPrice(ticketOffer.price.value) + " P"
            when (layoutPosition) {
                0 -> companyLogo.setImageResource(R.drawable.offer1)
                1 -> companyLogo.setImageResource(R.drawable.offer2)
                2 -> companyLogo.setImageResource(R.drawable.offer3)
                else -> companyLogo.visibility = View.GONE
            }

        }
        private fun formatPrice(price: Int): String {
            val formatted = String.format("%,d", price).replace(',', ' ')
            return formatted
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsOfferHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_offer, parent, false)
        return TicketsOfferHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("TicketsOffersAdapter", "getItemCount: ${ticketsOffers.size}")
        return if(ticketsOffers.size > 3) 3 else ticketsOffers.size
    }


    override fun onBindViewHolder(holder: TicketsOfferHolder, position: Int) {
        Log.d("TicketsOffersAdapter", "onBindViewHolder: $position")
        holder.bind(ticketsOffers[position])
    }
}