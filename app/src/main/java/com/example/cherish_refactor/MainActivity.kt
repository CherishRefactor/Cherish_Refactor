package com.example.cherish_refactor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cherish_refactor.util.PermissionUtil
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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater

        requestCherishPermissions()

        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            if(destination.id==R.id.home_fragment){
                if(intent.getStringExtra("setView").toString()=="Phone"){
                    Log.d("setVIew 333",intent.getStringExtra("setView").toString())
                   controller.navigate(R.id.action_main_home_to_phoneBookFragment)
                }
            }


            bottomNavigationView.isVisible =
                !(destination.id == R.id.calendarFragment || destination.id == R.id.aboutCherishFragment || destination.id == R.id.reviewFragment
                    || destination.id == R.id.detailModifyFragment || destination.id == R.id.plantFragment)

        }


    }
    private fun requestCherishPermissions() {
        PermissionUtil.requestCherishPermission(this, object : PermissionUtil.PermissionListener {
            override fun onPermissionGranted() {

            }

            override fun onPermissionShouldBeGranted(deniedPermissions: List<String>) {
                //shortToast(this@MainActivity, "권한 허용이 안되어있습니다. $deniedPermissions")
                openSettings()
            }

            override fun onAnyPermissionPermanentlyDenied(
                deniedPermissions: List<String>,
                permanentDeniedPermissions: List<String>
            ) {
                //shortToast(this@MainActivity, "권한 허용이 영구적으로 거부되었습니다. $permanentDeniedPermissions")
                openSettings()
            }
        })
    }
    private fun openSettings() {
        PermissionUtil.openPermissionSettings(this)
    }


}