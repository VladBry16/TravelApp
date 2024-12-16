package com.example.travelapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R

class ButtonsAdapter(private val buttons: List<String>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<ButtonsAdapter.ButtonViewHolder>() {
    class ButtonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val buttonTextView: TextView = view.findViewById(R.id.button_textview)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_button, parent, false)
        return ButtonViewHolder(view)
    }

    override fun getItemCount() = buttons.size
    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.buttonTextView.text = buttons[position]
        holder.buttonTextView.setOnClickListener{
            onItemClick(buttons[position])
        }
    }
}