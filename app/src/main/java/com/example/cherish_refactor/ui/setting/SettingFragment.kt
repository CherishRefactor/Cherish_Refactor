package com.example.cherish_refactor.ui.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cherish_refactor.MainActivity
import com.example.cherish_refactor.MainApplication
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentSettingBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.splash.SplashActivity
import com.example.cherish_refactor.util.AppLock
import com.example.cherish_refactor.util.AppLockConst
import com.example.cherish_refactor.util.MyKeyStore
import com.example.cherish_refactor.util.MyKeyStore.deleteToken
import com.example.cherish_refactor.util.MyKeyStore.deleteUserId
import com.example.cherish_refactor.util.MyKeyStore.deleteUserPassword
import com.example.cherish_refactor.util.MySharedPreference
import com.example.cherish_refactor.util.dialog.DeleteUserDialog

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val settingViewModel: SettingViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = settingViewModel
        setListener()
        requestSetting()
        setLock()



        return binding.root
    }
    fun setLock(){
        binding.settingLock.isChecked=MySharedPreference.getLockSwitch(requireContext())

    }


    private fun requestSetting(){
        settingViewModel.requestSettingUser(MyKeyStore.getUserId()!!)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            AppLockConst.ENABLE_PASSLOCK->{
                Toast.makeText(context,"암호설정",Toast.LENGTH_SHORT).show()
                requireActivity().finish()
                startActivity(Intent(requireActivity(),MainActivity::class.java))
            }
        }
    }

    private fun setListener(){
        binding.settingLock.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){ //켰을 때
                if(MySharedPreference.isFirstLock(requireContext())){ //앱 잠금이 처음이면
                    requireActivity().finish()
                    val intent = Intent(context, LockActivity::class.java).apply {
                        putExtra(AppLockConst.type, AppLockConst.ENABLE_PASSLOCK)
                    }
                    startActivityForResult(intent, AppLockConst.ENABLE_PASSLOCK)
                    MySharedPreference.saveFirstLock(requireContext())
                }
                MySharedPreference.setLockSwitch(requireContext(),true)

                buttonView.isChecked=true
            }else{ // 껐을 때
                MySharedPreference.setLockSwitch(requireContext(),false)
                buttonView.isChecked=false
            }
        }
        binding.constraintLayoutQuestion.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("Co.Cherishteam@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "체리쉬 문의")
            intent.putExtra(
                Intent.EXTRA_TEXT, "1. 문의 유형(문의, 버그 제보, 기타) :\n" +
                    "2. 회원 닉네임(필요시 기입) :\n" +
                    "3. 문의 내용 :\n" +
                    "\n" +
                    "문의하신 사항은 체리쉬팀이 신속하게 처리하겠습니다. 감사합니다 :)"
            )
            intent.type = "message/rfc822"
            startActivity(intent)
        }
        binding.constraintLayoutAboutCherish.setOnClickListener {
           // findNavController().navigate(R.id.action_main_setting_to_aboutCherishFragment)
           /* val intent = Intent(context,LockActivity::class.java).apply {
                putExtra(AppLockConst.type,AppLockConst.DISABLE_PASSLOCK)
            }
            startActivityForResult(intent,AppLockConst.hashCode())*/

            AppLock(requireContext()).removePassLock()
            MySharedPreference.removelock(requireContext())

        }

        binding.constraintLayoutInfo.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.notion.so/Cherish-2d35c1bffa2f4d49943db302d76e3cac")
            )
            startActivity(intent)
        }

        binding.constraintLayoutService.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.notion.so/Cherish-d96f88172ffa4d80b257665849bddc65")
            )
            startActivity(intent)
        }
        binding.settingNextNickname.setOnClickListener {
            findNavController().navigate(R.id.action_main_setting_to_userModifyFragment)
        }
        binding.settingUsernickname.setOnClickListener {

            findNavController().navigate(R.id.action_main_setting_to_userModifyFragment)
        }
        binding.friendsCons.setOnClickListener { // 로그아웃 하기
            Toast.makeText(requireContext(), "로그아웃 되었습니다.",Toast.LENGTH_SHORT).show()

            MainApplication.apply {
                deleteToken()
                deleteUserId()
                deleteUserPassword()
            }

            requireActivity().finish()
            val intent = Intent(requireActivity(), SplashActivity::class.java)
            startActivity(intent)
        }
        binding.quit.setOnClickListener {

            // 회원탈퇴하기
            DeleteUserDialog {
                settingViewModel.requestUserDelete(MyKeyStore.getUserId()!!)
            }.show(childFragmentManager, "WITHDRAWAL")




        }

        binding.settingAlarmSetting.setOnCheckedChangeListener { buttonView, isChecked ->

        }
    }





}