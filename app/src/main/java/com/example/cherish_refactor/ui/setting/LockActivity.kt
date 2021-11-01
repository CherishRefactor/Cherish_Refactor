package com.example.cherish_refactor.ui.setting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.ActivityLockBinding
import com.example.cherish_refactor.ui.base.BaseActivity
import com.example.cherish_refactor.ui.signin.SignInActivity
import com.example.cherish_refactor.util.AppLock
import com.example.cherish_refactor.util.AppLockConst

class LockActivity : BaseActivity<ActivityLockBinding>(R.layout.activity_lock) {

    private val viewModel:LockViewModel by viewModels()

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
    var current = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm=viewModel


        val buttonArray = arrayListOf<Button>(binding.btn0,binding.btn1,binding.btn2,binding.btn3,binding.btn4,binding.btn5
        ,binding.btn6,binding.btn7,binding.btn8,binding.btn9)
        val buttonBack = findViewById<ImageButton>(R.id.btn1_back)

       /* for(button in buttonArray){
            button.setOnClickListener { btnListener }
        }
        buttonBack.setOnClickListener { btnListener }*/

        binding.btn0.setOnClickListener {
            current = 0
            make()
        }
        binding.btn1.setOnClickListener {
            current = 1
            make()
        }
        binding.btn2.setOnClickListener {
            current = 2
            make()
        }
        binding.btn3.setOnClickListener {
            current = 3
            make()
        }
        binding.btn4.setOnClickListener {
            current = 4
            make()
        }
        binding.btn5.setOnClickListener {
            current = 5
            make()
        }
        binding.btn6.setOnClickListener {
            current = 6
            make()
        }
        binding.btn7.setOnClickListener {
            current = 7
            make()
        }
        binding.btn8.setOnClickListener {
            current = 8
            make()
        }
        binding.btn9.setOnClickListener {
            current = 9
            make()
        }
        binding.btn1Back.setOnClickListener {
            onDeleteKey()
            //make()
        }

        setListener()
        setContentView(binding.root)

    }
    fun make(){
        val strCurrent = current.toString()
        count += 1
        if (current != -1) {
            if (count == 1) {
                viewModel.imgPw1.value=true
                Log.d("lock1234","1")
                pwd1 = strCurrent
            }
            if (count == 2) {
                viewModel.imgPw2.value=true
                Log.d("lock1234","2")
                pwd2 = strCurrent
            }
            if (count == 3) {
                viewModel.imgPw3.value=true
                Log.d("lock1234","3")
                pwd3 = strCurrent
            }
            if (count == 4) {
                viewModel.imgPw4.value=true
                Log.d("lock1234","4")
                pwd4 = strCurrent
                inputType(intent.getIntExtra("type",0))
            }

        }
    }

    fun setListener(){

        binding.startBtn.setOnClickListener {

            if(viewModel.isLockOk.value == true){
                AppLock(this).setPassLock(inputedPassword())
                setResult(Activity.RESULT_OK)
                finish()
                startActivity(Intent(this,SignInActivity::class.java))
            }else{
                Toast.makeText(this,"비밀번호 틀림!",Toast.LENGTH_SHORT).show()
            }

        }
    }



    fun onDeleteKey(){
        if(viewModel.imgPw4.value == true){
            viewModel.imgPw4.value=false

            pwd4=""
            count-=1

        }
        else if(viewModel.imgPw3.value==true){
            viewModel.imgPw3.value=false
            pwd3=""

            count-=1


        }
        else if(viewModel.imgPw2.value==true){
            viewModel.imgPw2.value=false
            pwd2=""
            count-=1

        }
        else if(viewModel.imgPw1.value==true){
            viewModel.imgPw1.value=false
            pwd1=""
            count-=1

        }

    }

    fun onClear(){
        viewModel.imgPw1.value=false
        viewModel.imgPw2.value=false
        viewModel.imgPw3.value=false
        viewModel.imgPw4.value=false
        pwd1=""
        pwd2=""
        pwd3=""
        pwd4=""


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
                    count = 0
                    //finish()
                    Toast.makeText(this,"다시 한번 입력",Toast.LENGTH_SHORT).show()

                }
                else{
                    if(oldPwd == inputedPassword()){
                        /*AppLock(this).setPassLock(inputedPassword())
                        setResult(Activity.RESULT_OK)
                        finish()*/
                        viewModel.isLockOk.value=true
                        Toast.makeText(this,"비밀번호 일치",Toast.LENGTH_SHORT).show()


                    }
                    else{
                        onClear()
                        oldPwd=""
                        binding.textView.text="비밀번호를 확인해주세요."
                        Toast.makeText(this,"비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show()
                    }

                }

            }
            AppLockConst.UNLOCK_PASSWORD ->{
                if(AppLock(this).checkPassLock(inputedPassword())){
                    viewModel.isLockOk.value=true
                   // setResult(Activity.RESULT_OK)
                    //finish()
                    count = 0
                }else{
                    onClear()
                    count = 0
                }
            }




        }


    }
}
