package com.example.awai

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class BeingMeanFragment : Fragment(R.layout.fragment_someone_being_mean) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val confirmBtn = view.findViewById<ImageView>(R.id.confirmBtn)
        val backBtn = view.findViewById<ImageView>(R.id.closeBtn)

        backBtn.setOnClickListener{
            findNavController().navigate(R.id.action_beingMeanFragment_to_homeFragment)
        }

        confirmBtn.setOnClickListener{
            findNavController().navigate(R.id.action_beingMeanFragment_to_contactFragment)
        }
    }
}