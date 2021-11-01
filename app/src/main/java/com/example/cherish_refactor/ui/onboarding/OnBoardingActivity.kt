package com.example.cherish_refactor.ui.onboarding


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.ActivityOnboardingBinding
import com.example.cherish_refactor.ui.base.BaseActivity
import com.example.cherish_refactor.ui.setting.LockActivity
import com.example.cherish_refactor.ui.signin.SignInActivity
import com.example.cherish_refactor.util.AppLockConst
import com.example.cherish_refactor.util.MySharedPreference
import com.sopt.cherish.ui.main.onboarding.OnboardingFragment

class OnBoardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    //private val onBoardingViewModel:OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding.vm = onBoardingViewModel


        if (!MySharedPreference.isFirstEnter(this)) {
            startMainActivity()
            if(MySharedPreference.getLockSwitch(this)){
                finish()
                val intent = Intent(this, LockActivity::class.java).apply {
                    putExtra(AppLockConst.type, AppLockConst.UNLOCK_PASSWORD)
                }
                startActivityForResult(intent, AppLockConst.UNLOCK_PASSWORD)
            }else {
                startMainActivity()
            }
            return
        }




        setFragment(OnboardingFragment())

        setContentView(binding.root)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            AppLockConst.UNLOCK_PASSWORD->{
                Toast.makeText(this,"암호설정", Toast.LENGTH_SHORT).show()
                finish()
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }
    }

    fun startMainActivity(){
        startActivity(Intent(this@OnBoardingActivity, SignInActivity::class.java))
    }



    fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.onBoardingFragment, fragment)
        transaction.commit()
    }
}