package com.example.cherish_refactor.ui.setting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.ActivityLockBinding
import com.example.cherish_refactor.ui.base.BaseActivity
import com.example.cherish_refactor.ui.signin.SignInActivity
import com.example.cherish_refactor.util.AppLock
import com.example.cherish_refactor.util.AppLockConst

class LockActivity : BaseActivity<ActivityLockBinding>(R.layout.activity_lock) {

    private var oldPwd= ""

    private var changePwdUnlock = false
    var count = 0
    var imgPw1=false
    var imgPw2=false
    var imgPw3=false
    var imgPw4=false

    var pwd1 = ""
    var pwd2 = ""
    var pwd3 = ""
    var pwd4 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock)

        val buttonArray = arrayListOf<Button>(binding.btn0,binding.btn1,binding.btn2,binding.btn3,binding.btn4,binding.btn5
        ,binding.btn6,binding.btn7,binding.btn8,binding.btn9)
        val buttonBack = findViewById<ImageButton>(R.id.btn1_back)
        for(button in buttonArray){
            button.setOnClickListener { btnListener }
        }
        buttonBack.setOnClickListener { btnListener }

    }
    fun setListener(){
        binding.startBtn.setOnClickListener {
            finish()
            startActivity(Intent(this,SignInActivity::class.java))
        }
    }
    val btnListener = View.OnClickListener {
        var current = -1

        if (count < 5) {
            when (it.id) {
                R.id.btn0 -> {
                    current = 0
                }
                R.id.btn1 -> current = 1
                R.id.btn2 -> current = 2
                R.id.btn3 -> current = 3
                R.id.btn4 -> current = 4
                R.id.btn5 -> current = 5
                R.id.btn6 -> current = 6
                R.id.btn7 -> current = 7
                R.id.btn8 -> current = 8
                R.id.btn9 -> current = 9
                R.id.btn1_back -> onDeleteKey()
            }
        }
        val strCurrent = current.toString()

        count += 1
        if (current != -1) {
            if (count == 1) {
                imgPw1 = true

                pwd1 = strCurrent
            }
            if (count == 2) {
                imgPw2 = true
                pwd2 = strCurrent
            }
            if (count == 3) {
                imgPw3 = true
                pwd3 = strCurrent
            }
            if (count == 4) {
                imgPw4 = true
                pwd4 = strCurrent
                inputType(intent.getIntExtra("type",0))
            }

        }

    }


    fun onDeleteKey(){
        if(imgPw4){
            imgPw4=false
            pwd4=""

        }
        if(imgPw3){
            imgPw3=false
            pwd3=""

        }
        if(imgPw2){
            imgPw2=false
            pwd2=""

        }
        if(imgPw1){
            imgPw1=false
            pwd1=""

        }

    }

    fun onClear(){
        binding.imgPw1.setImageResource(R.drawable.ic_password)
        binding.imgPw2.setImageResource(R.drawable.ic_password)
        binding.imgPw3.setImageResource(R.drawable.ic_password)
        binding.imgPw4.setImageResource(R.drawable.ic_password)

    }
    fun inputedPassword():String{
        return pwd1+pwd2+pwd3+pwd4
    }



    fun inputType(type:Int){
        when(type){
            AppLockConst.ENABLE_PASSLOCK ->{
                if(oldPwd.isEmpty()){
                    oldPwd=inputedPassword()
                    onClear()

                }
                else{
                    if(oldPwd == inputedPassword()){
                        AppLock(this).setPassLock(inputedPassword())
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                    else{
                        onClear()
                        oldPwd=""
                    }

                }

            }
            AppLockConst.UNLOCK_PASSWORD ->{
                if(AppLock(this).checkPassLock(inputedPassword())){
                    setResult(Activity.RESULT_OK)
                    finish()
                }else{
                    onClear()
                }
            }




        }


    }
}
