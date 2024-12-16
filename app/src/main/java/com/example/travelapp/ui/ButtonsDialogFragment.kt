package com.example.travelapp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ButtonsDialogFragment : DialogFragment() {

    interface ButtonsDialogListener {
        fun onButtonSelected(button: String)
    }

    private var listener: ButtonsDialogListener? = null
    private val buttons = arrayOf("Сложный маршрут", "Куда угодно", "Выходные", "Горячие билеты")


    fun setButtonsDialogListener(listener: ButtonsDialogListener) {
        this.listener = listener
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setItems(buttons) { dialog, which ->
                val selectedButton = buttons[which]
                if(selectedButton == "Куда угодно") {
                    listener?.onButtonSelected(selectedButton)
                    dialog.dismiss()
                } else {
                    val stubDialogFragment = StubDialogFragment()
                    stubDialogFragment.show(childFragmentManager, StubDialogFragment.TAG)
                    dialog.dismiss()
                    listener?.onButtonSelected(selectedButton)
                }
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        const val TAG = "ButtonsDialogFragment"
    }
}