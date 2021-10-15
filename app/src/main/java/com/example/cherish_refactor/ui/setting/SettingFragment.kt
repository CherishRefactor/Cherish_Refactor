package com.example.cherish_refactor.ui.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentSettingBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.splash.SplashActivity


/**
 * 환경 설정 뷰
 */
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val settingViewModel: SettingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = settingViewModel
        setListener()

        return binding.root
    }

    private fun setListener(){
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
        }
        binding.settingUsernickname.setOnClickListener {
        }
        binding.friendsCons.setOnClickListener {

            requireActivity().finish()
            val intent = Intent(requireActivity(), SplashActivity::class.java)
            startActivity(intent)
        }
        binding.quit.setOnClickListener {

        }
        binding.settingAlarmSetting.setOnCheckedChangeListener { buttonView, isChecked ->

        }
    }





}