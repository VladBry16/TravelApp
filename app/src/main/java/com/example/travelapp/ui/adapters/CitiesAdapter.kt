package com.example.travelapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R

class CitiesAdapter(private val cities: List<String>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {
    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityTextView: TextView = view.findViewById(R.id.city_textview)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_city, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount() = cities.size
    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.cityTextView.text = cities[position]
        holder.cityTextView.setOnClickListener {
            onItemClick(cities[position])
        }
    }
}