package com.example.travelapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.models.Offer
import com.example.travelapp.network.TravelApi
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FlightsFragment : Fragment(), CityDialogFragment.CityDialogListener {

    private val travelApi: TravelApi by inject()
    private lateinit var fromCityEditText: EditText
    private lateinit var toCityEditText: EditText
    private lateinit var offersRecyclerView: RecyclerView
    private var selectedCity: String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_flights, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fromCityEditText = view.findViewById(R.id.from_city_edittext)
        toCityEditText = view.findViewById(R.id.to_city_edittext)
        offersRecyclerView = view.findViewById(R.id.offers_recyclerview)

        fromCityEditText.setOnClickListener {
            //todo: open dialog to select a city
        }
        fromCityEditText.setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_UP) {
                if(event.rawX >= fromCityEditText.right - fromCityEditText.compoundDrawables[2].bounds.width()) {
                    fromCityEditText.text.clear()
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }


        toCityEditText.setOnClickListener {
            showCityDialog()
        }

        toCityEditText.setOnFocusChangeListener{_, hasFocus ->
            if (hasFocus) {
                showCityDialog()
            }
        }
        toCityEditText.setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_UP) {
                if(event.rawX >= toCityEditText.right - toCityEditText.compoundDrawables[2].bounds.width()) {
                    toCityEditText.text.clear()
                    selectedCity = null
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }



        lifecycleScope.launch {
            loadOffers()
        }
    }
    private suspend fun loadOffers() {
        val url = "https://drive.usercontent.google.com/u/0/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download"
        val offers = travelApi.getOffers(url).offers
        setupOffersRecyclerView(offers)
    }
    private fun setupOffersRecyclerView(offers: List<Offer>) {
        offersRecyclerView.adapter = OffersAdapter(offers)
    }
    private fun showCityDialog() {
        val dialog = CityDialogFragment()
        dialog.setCityDialogListener(this)
        dialog.show(childFragmentManager, CityDialogFragment.TAG)
    }

    override fun onCitySelected(city: String) {
        toCityEditText.setText(city)
        selectedCity = city
        if (city == "Куда угодно") {
            return
        }
        if(city.isNotEmpty()){
            findNavController().navigate(R.id.menu_search, Bundle().apply {
                putString("selectedCity", city)
            })
        }
    }
}