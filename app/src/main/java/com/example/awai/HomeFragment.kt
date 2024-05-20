package com.example.awai

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val panicButton = view.findViewById<Button>(R.id.panicButton)

        panicButton.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_confirmationFragment)
        }
    }
}