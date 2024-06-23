package com.example.awai

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class ContactManagementFragment : Fragment(R.layout.fragment_contact_management) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addBtn = view.findViewById<Button>(R.id.addBtn)
        addBtn.setOnClickListener{
            Log.i("test","test")
        }
    }
}