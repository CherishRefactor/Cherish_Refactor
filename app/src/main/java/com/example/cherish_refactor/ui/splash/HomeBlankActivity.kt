package com.example.cherish_refactor.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.cherish_refactor.MainActivity
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.ActivityHomeBlankBinding
import com.example.cherish_refactor.ui.base.BaseActivity


class HomeBlankActivity : BaseActivity<ActivityHomeBlankBinding>(R.layout.activity_home_blank) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setListener()

    }

    fun setListener(){
        binding.startBtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("setView","Phone")
            startActivity(intent)
            finish()
        }
    }

}