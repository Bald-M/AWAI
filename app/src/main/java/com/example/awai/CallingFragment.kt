package com.example.awai

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.database.Cursor
import android.telephony.SmsManager
import android.util.Log

class CallingFragment : Fragment(R.layout.fragment_calling) {
    // 3 sec = 3000
    private var countdownTime: Long = 3000
    private var countdownTimer: CountDownTimer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countdownTV = view.findViewById<TextView>(R.id.countdownTV)
        var cancelBtn = view.findViewById<Button>(R.id.cancelBtn)

        cancelBtn.setOnClickListener {
            // When tap "I am safe button", countdown must be canceled
            countdownTimer?.cancel()
            findNavController().navigate(R.id.action_callingFragment_to_homeFragment)
            // Need to fix when bot tab changed, countdown must be canceled as well
        }

        startCountdown(countdownTV)


    }

    fun startCountdown(countdownTV: TextView) {
        countdownTimer = object : CountDownTimer(countdownTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                countdownTV.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                sendMessage()
                // After times over, goes to notification fragment
                findNavController().navigate(R.id.action_callingFragment_to_notificationFragment)
            }
        }
        // Start countdown
        countdownTimer?.start()
    }

    fun sendMessage() {
        val message = "Help! I need help right away. I'm at [your specific location, like home, school, or park]. Something bad is happening [describe the situation briefly, like someone is hurt or there's a fire]. Please send help quickly! I'm scared and need help now."

        val context: Context? = getActivity()
        val dbHelper = context?.let { DatabaseHelper(it) }
        val cursor: Cursor = dbHelper!!.allData
        val phoneNumbers = mutableListOf<String>()


//        val number = "123"
//        val text = "I love you"
//
//        val smsManager = SmsManager.getDefault()
//        smsManager.sendTextMessage(number, null, text, null, null)
//        println("Sent Succeed")


        if (cursor.moveToFirst()) {
            do {
                val number = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
                phoneNumbers.add(number)
            } while (cursor.moveToNext())
        } else {
            Log.d("SMS", "No contacts found in database")
        }
        cursor.close()


//        for (number in phoneNumbers) {
//            println(number)
//            try {
//                val smsManager: SmsManager = SmsManager.getDefault()
//                smsManager.sendTextMessage(number, null, message, null, null)
//                Log.i("SMS", "Sent Succeed to $number")
//            } catch (e: Exception) {
//                Log.i("SMS", "Failed to send SMS to $number: ${e.message}")
//            }
//        }

        val testNumber = "126"
        val testText = "Help me"
        try {
            val smsManager = SmsManager.getDefault()

            for (number in phoneNumbers) {
                smsManager.sendTextMessage(number, null, testText, null, null)
            }

            Log.i("SMS", "Sent Succeed to $testNumber")
        } catch (e: Exception) {
            Log.e("SMS", "Failed to send SMS to $testNumber: ${e.message}")
        }

    }
}