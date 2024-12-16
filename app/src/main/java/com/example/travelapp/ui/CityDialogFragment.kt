package com.example.travelapp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.ui.adapters.ButtonsAdapter
import com.example.travelapp.ui.adapters.CitiesAdapter

class CityDialogFragment : DialogFragment() {

    interface CityDialogListener {
        fun onCitySelected(city: String)
    }

    private var listener: CityDialogListener? = null
    private val cities = arrayOf("Стамбул", "Сочи", "Пхукет", "Москва", "Минск")
    private val buttons = arrayOf("Сложный маршрут", "Куда угодно", "Выходные", "Горячие билеты")


    fun setCityDialogListener(listener: CityDialogListener) {
        this.listener = listener
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_city, null)
            builder.setView(view)

            val buttonsRecyclerView = view.findViewById<RecyclerView>(R.id.buttons_recyclerview)
            buttonsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            buttonsRecyclerView.adapter = ButtonsAdapter(buttons.toList()) {
                listener?.onCitySelected(it)
                dialog?.dismiss()
            }
            val citiesRecyclerView = view.findViewById<RecyclerView>(R.id.cities_recyclerview)
            citiesRecyclerView.layoutManager = LinearLayoutManager(context)
            citiesRecyclerView.adapter = CitiesAdapter(cities.toList()) {
                listener?.onCitySelected(it)
                dialog?.dismiss()
            }
            val titleView = view.findViewById<TextView>(android.R.id.title)
            titleView?.setTextColor(resources.getColor(R.color.white))


            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    companion object {
        const val TAG = "CityDialogFragment"
    }
}