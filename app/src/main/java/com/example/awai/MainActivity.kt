package com.example.awai

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent

        val age = intent.getStringExtra("age")?.toInt() ?: 0
        val language = intent.getStringExtra("language")


        setAppLocale(language, age)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        val navHostFragment = findViewById<FragmentContainerView>(R.id.nav_host_fragment)

        val ageGroup = if (age in 5..12) {
            "child"
        } else {
            "teen"
        }

//        val bundle = Bundle().apply {
//            putString("ageGroup", ageGroup)
//        }
//        navHostFragment.findNavController().setGraph(R.navigation.nav_graph, bundle)

        // Switch Bot Nav
        bottomNav.setOnItemSelectedListener {
                item -> when(item.itemId) {

            R.id.homeFragment -> {
                navHostFragment.findNavController().navigate(R.id.homeFragment)
                true
            }

            // Contact Management Fragment
            R.id.contactManagementFragment -> {
                navHostFragment.findNavController().navigate(R.id.contactManagementFragment)
                true
            }

            // Setting Fragment
            R.id.settingFragment -> {
                navHostFragment.findNavController().navigate(R.id.settingFragment)
                true
            }

            else -> false

        }
        }
    }

    private fun setAppLocale(language: String?, age: Int) {
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