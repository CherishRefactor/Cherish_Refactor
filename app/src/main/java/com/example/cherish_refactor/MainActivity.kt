package com.example.cherish_refactor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.nav_view)

        val navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)?.findNavController()
        navController?.let {
            bottomNavigationView.setupWithNavController(navController)
        }


        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            bottomNavigationView.isVisible =
                !(destination.id == R.id.calendarFragment || destination.id == R.id.aboutCherishFragment || destination.id == R.id.reviewFragment
                    || destination.id == R.id.detailModifyFragment)

        }

    }



}