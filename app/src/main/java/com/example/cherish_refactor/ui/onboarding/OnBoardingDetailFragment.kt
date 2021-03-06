package com.example.cherish_refactor.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.cherish_refactor.databinding.FragmentOnboardingDetailBinding
import com.example.cherish_refactor.ui.signin.SignInActivity
import com.example.cherish_refactor.util.MySharedPreference

class OnBoardingDetailFragment(val index: Int) : Fragment() {

    lateinit var binding: FragmentOnboardingDetailBinding
    var activity = OnBoardingActivity()

    //private val onBoardingViewModel:OnBoardingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingDetailBinding.inflate(inflater, container, false)

        setListener()

        return binding.root
    }

    fun setListener(){
        binding.onBoardingBtn.setOnClickListener {
            startActivity(Intent(context, SignInActivity::class.java))
            requireActivity().finish()
            MySharedPreference.saveFirstEnter(requireContext())
        }
        binding.skipBtn.setOnClickListener {
            startActivity(Intent(context, SignInActivity::class.java))
            requireActivity().finish()
            MySharedPreference.saveFirstEnter(requireContext())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (index) {
            1 -> {
                binding.imageViewOnBoarding.isVisible = true

            }
            2 -> {
                binding.imageViewOnBoarding.isVisible = false
                binding.imageViewOnBoarding2.isVisible = true
                binding.textViewOnBoarding.text = "식물을 추천해요"
                binding.textViewOnBoarding2.text = "내 소중한 사람의 연락주기와\n꼭 맞는 물주기를 가진 식물이 매칭돼요."
            }
            3 -> {
                binding.imageViewOnBoarding2.isVisible = false
                binding.imageViewOnBoarding3.isVisible = true
                binding.textViewOnBoarding.text = "알림을 받아요!"
                binding.textViewOnBoarding2.text = "주기가 다가올 때마다\n" +
                        "‘연락하기’ 알림을 받아요."
            }
            4 -> {
                binding.imageViewOnBoarding3.isVisible = false
                binding.imageViewOnBoarding4.isVisible = true
                binding.textViewOnBoarding.text = "연락을 기록해요"
                binding.textViewOnBoarding2.text = "오늘의 연락을\n" +
                        "키워드와 메모로 기록해요."
            }
            5 -> {
                binding.imageViewOnBoarding4.isVisible = false
                binding.imageViewOnBoarding5.isVisible = true
                binding.textViewOnBoarding.text = "당신의 소중한 사람을 위한\n 꾸준한 식물 키우기"
                binding.textViewOnBoarding2.text = "지금 바로 시작할까요?"
                binding.onBoardingBtn.isVisible = true
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnBoardingActivity)
            activity = context

    }


}