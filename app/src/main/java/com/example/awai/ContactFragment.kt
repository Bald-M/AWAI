package com.example.awai

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ContactFragment : Fragment(R.layout.fragment_contact) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Should add a return to the menu button

        val sendBtn = view.findViewById<Button>(R.id.sendBtn)

        sendBtn.setOnClickListener {
            findNavController().navigate(R.id.action_contactFragment_to_callingFragment)
        }

    }
}