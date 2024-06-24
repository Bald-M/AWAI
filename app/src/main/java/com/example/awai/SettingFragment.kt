package com.example.awai

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import java.util.Locale
import android.content.res.Configuration
import android.util.Log
import android.widget.Switch
import android.widget.TextView
import androidx.cardview.widget.CardView


class SettingFragment : Fragment(R.layout.fragment_setting) {

    private lateinit var languageSwitch: Switch
    private lateinit var languageToggleSwitch: Switch
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var languageTV: TextView

    private var switchChecked: Boolean = false
    private var switchToggleChecked: Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        languageSwitch = view.findViewById<Switch>(R.id.languageSwitch)
        languageToggleSwitch = view.findViewById<Switch>(R.id.languageToggleSwitch)
        languageTV = view.findViewById<TextView>(R.id.languageTV)

        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // 恢复开关状态
        val switchChecked = sharedPreferences.getBoolean("switchChecked", false)
        languageSwitch.isChecked = switchChecked

        val switchToggleChecked = sharedPreferences.getBoolean("switchToggleChecked", false)
        languageToggleSwitch.isChecked = switchToggleChecked


        val age = arguments?.getString("age")?.toInt() ?: 0
        val language = arguments?.getString("language")


        languageSwitch.setOnCheckedChangeListener{ buttonView, isChecked ->
            // 保存开关状态
            // Save switch status
            sharedPreferences.edit().putBoolean("switchChecked", isChecked).apply()
            // Child Mode
            if (isChecked) {
                setAppLocale(language!!, 1)
            } else {
                setAppLocale(language!!, 12)
            }
        }

        languageToggleSwitch.setOnCheckedChangeListener{ buttonView, isChecked ->
            // 保存开关状态
            // Save switch status
            sharedPreferences.edit().putBoolean("switchToggleChecked", isChecked).apply()
            // Maori
            if (isChecked) {
                languageTV.text = "Maori"
                setAppLocale("Maori", age)
            } else {
                languageTV.text = "English"
                setAppLocale("English", age)
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("switchChecked", switchChecked)
        outState.putBoolean("switchToggleChecked", switchToggleChecked)
    }


    fun showLanguageSelectionDialog(language: String, age:Int) {
        val languages = arrayOf("English", "Māori")

        AlertDialog.Builder(requireContext())
            .setTitle("Select Language")
            .setItems(languages) { dialog, which ->
                // Set the selected language
                setAppLocale(language, age)
            }
            .create()
            .show()
    }

    private fun setAppLocale(language: String, age: Int) {
        val locale = when {
            language == "English" && age < 12 -> Locale.ENGLISH
            language == "Maori" && age < 12 -> Locale("mi")
            language == "English" && age >= 12 -> Locale("es")
            language == "Maori" && age >= 12 -> Locale("cn")
            else -> Locale.getDefault()
        }

        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}