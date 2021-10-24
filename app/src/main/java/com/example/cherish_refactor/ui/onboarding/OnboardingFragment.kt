package com.sopt.cherish.ui.main.onboarding

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentOnboardingBinding
import com.example.cherish_refactor.ui.onboarding.OnBoardingDetailFragment
import com.example.cherish_refactor.ui.onboarding.adapter.OnBoardingViewPagerAdapter
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

class OnboardingFragment : Fragment() {

    lateinit var binding: FragmentOnboardingBinding
    private lateinit var viewpagerAdapter: OnBoardingViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        initializeViewPager(binding)
        initializeIndicatorView(binding)

        binding.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                binding.indicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                binding.indicatorView.onPageSelected(position)
                Log.d("ppppppp", position.toString())
                binding.indicatorView.isVisible = true
                if (position == 4) {
                    binding.indicatorView.isVisible = false
                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })



        return binding.root

    }


    private fun initializeViewPager(binding: FragmentOnboardingBinding) {
        viewpagerAdapter = OnBoardingViewPagerAdapter(childFragmentManager)

        viewpagerAdapter.fragments = listOf(
            OnBoardingDetailFragment(1),
            OnBoardingDetailFragment(2),
            OnBoardingDetailFragment(3),
            OnBoardingDetailFragment(4),
            OnBoardingDetailFragment(5)
        )

        binding.viewpager.adapter = viewpagerAdapter
    }

    fun initializeIndicatorView(binding: FragmentOnboardingBinding) {
        binding.indicatorView.apply {
            setSliderColor(Color.parseColor("#c4c4c4"), Color.parseColor("#31d693"))
            setSliderWidth(resources.getDimension(R.dimen.margin_7dp))
            setSliderHeight(resources.getDimension(R.dimen.margin_7dp))
            setSlideMode(IndicatorSlideMode.NORMAL)
            setIndicatorStyle(IndicatorStyle.CIRCLE)
            setPageSize(binding.viewpager.adapter!!.count)
            notifyDataChanged()
        }
    }

}