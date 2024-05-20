package com.example.awai

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class CallingFragment : Fragment(R.layout.fragment_calling) {
    // 3 sec = 3000
    private var countdownTime : Long = 3000
    private var countdownTimer: CountDownTimer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countdownTV = view.findViewById<TextView>(R.id.countdownTV)
        var cancelBtn = view.findViewById<Button>(R.id.cancelBtn)

        cancelBtn.setOnClickListener{
            // When tap "I am safe button", countdown must be canceled
            countdownTimer?.cancel()
            findNavController().navigate(R.id.action_callingFragment_to_homeFragment)
            // Need to fix when bot tab changed, countdown must be canceled as well
        }

        startCountdown(countdownTV)

    }

    fun startCountdown(countdownTV: TextView) {
        countdownTimer = object : CountDownTimer(countdownTime, 1000) {
            override fun onTick(millisUntilFinished : Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                countdownTV.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                // After times over, goes to notification fragment
                 findNavController().navigate(R.id.action_callingFragment_to_notificationFragment)
            }
        }
        // Start countdown
        countdownTimer?.start()
    }
}