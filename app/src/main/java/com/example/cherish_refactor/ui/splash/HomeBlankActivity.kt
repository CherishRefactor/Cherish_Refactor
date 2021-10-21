package com.example.cherish_refactor.ui.splash

import android.os.Bundle
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

        }
    }

}