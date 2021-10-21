package com.example.cherish_refactor.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentAboutCherishBinding
import com.example.cherish_refactor.ui.base.BaseFragment

class AboutCherishFragment : BaseFragment<FragmentAboutCherishBinding>(R.layout.fragment_about_cherish) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }
}