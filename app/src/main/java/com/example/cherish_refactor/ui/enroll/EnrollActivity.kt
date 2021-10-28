package com.example.cherish_refactor.ui.enroll

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.ActivityEnrollBinding

class EnrollActivity : AppCompatActivity() {

    lateinit var binding:ActivityEnrollBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEnrollBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 홈에서 누르는지 블랭크에서 누르는지 처리


        initializeFragment()

    }

    private fun initializeFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.enroll_frame, PhoneBookFragment()).commit()
    }
}