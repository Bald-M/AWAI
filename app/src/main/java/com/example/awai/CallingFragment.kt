package com.example.awai

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class CallingFragment : Fragment(R.layout.fragment_calling) {
    // 3 sec = 3000
    private var countdownTime : Long = 3000

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countdownTV = view.findViewById<TextView>(R.id.countdownTV)

        startCountdown(countdownTV)

    }

    fun startCountdown(countdownTV: TextView) {
        var countdownTimer = object : CountDownTimer(countdownTime, 1000) {
            override fun onTick(millisUntilFinished : Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                countdownTV.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                // After times over, goes to notification fragment
                 findNavController().navigate(R.id.action_callingFragment_to_notificationFragment)
            }
        }

        countdownTimer?.start()
    }
}