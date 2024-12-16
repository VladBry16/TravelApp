package com.example.travelapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.models.Offer

class OffersAdapter(private val offers: List<Offer>): RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false)
        return OfferViewHolder(view)
    }

    override fun getItemCount(): Int = offers.size

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = offers[position]
        holder.bind(offer)
    }
    inner class OfferViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val offerImageView: ImageView = view.findViewById(R.id.offer_imageview)
        private val offerTitleTextView: TextView = view.findViewById(R.id.offer_title_textview)
        private val offerTownTextView: TextView = view.findViewById(R.id.offer_town_textview)
        private val offerPriceTextView: TextView = view.findViewById(R.id.offer_price_textview)

        fun bind(offer: Offer) {
            offerTitleTextView.text = offer.title
            offerTownTextView.text = offer.town
            offerPriceTextView.text = offer.price.value.toString()
            when (offer.id) {
                1 -> offerImageView.setImageResource(R.drawable.offer1)
                2 -> offerImageView.setImageResource(R.drawable.offer2)
                3 -> offerImageView.setImageResource(R.drawable.offer3)
            }
        }
    }
}