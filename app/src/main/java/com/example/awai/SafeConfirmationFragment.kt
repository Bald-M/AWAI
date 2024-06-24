package com.example.awai

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SafeConfirmationFragment : Fragment(R.layout.fragment_safe_confirmation) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val confirmBtn = view.findViewById<ImageView>(R.id.confirmBtn)
        val backBtn = view.findViewById<ImageView>(R.id.closeBtn)
        val emergencyBtn = view.findViewById<Button>(R.id.emergencyBtn)

        backBtn.setOnClickListener{
            findNavController().navigate(R.id.action_safeConfirmationFragment_to_homeFragment)
        }

        confirmBtn.setOnClickListener{
            findNavController().navigate(R.id.action_safeConfirmationFragment_to_grownUpFragment)
        }


    }
}