package com.example.awai

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ConfirmationFragment : Fragment(R.layout.fragment_confirmation) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancelBtn = view.findViewById<ImageView>(R.id.closeBtn)
        val confirmBtn = view.findViewById<ImageView>(R.id.confirmBtn)

        cancelBtn.setOnClickListener{
            findNavController().navigate(R.id.action_confirmationFragment_to_hurtConfirmationFragment)
        }

        confirmBtn.setOnClickListener{
            findNavController().navigate(R.id.action_confirmationFragment_to_contactFragment)
        }
    }
}