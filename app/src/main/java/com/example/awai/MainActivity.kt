package com.example.awai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        val navHostFragment = findViewById<FragmentContainerView>(R.id.nav_host_fragment)

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
}