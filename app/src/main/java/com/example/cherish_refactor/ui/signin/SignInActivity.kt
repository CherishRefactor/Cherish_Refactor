package com.example.cherish_refactor.ui.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.cherish_refactor.MainActivity
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.ActivitySignInBinding
import com.example.cherish_refactor.ui.base.BaseActivity
import com.example.cherish_refactor.ui.splash.HomeBlankActivity
import com.example.cherish_refactor.util.MyKeyStore

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {


    private val signInViewModel :SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = signInViewModel
        setListener()
        observer()
        autoLogin()

    }
    fun login(){
        binding.loginBtn.setOnClickListener {
            signInViewModel.requestLogIn()
            //MySharedPreference.setUserId(this, binding.editTextTextPersonName.toString())

        }
    }

    fun setListener(){
        binding.textView31.setOnClickListener {

        }

        binding.textView30.setOnClickListener {

        }

        binding.textView4.setOnClickListener {

        }
    }

    fun observer(){
        signInViewModel.isFirst.observe(this){
            if(it==0){
                startActivity(Intent(this, HomeBlankActivity::class.java))
            }else{
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun autoLogin() {
        Log.d("autologin1111",MyKeyStore.getUserId().toString())
        //login()
        // 자동 로그인이 되고 아무것도 없으면 블랭크 있으면 메인
        if(MyKeyStore.getUserId()==-1) {

            login()
        }else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

}