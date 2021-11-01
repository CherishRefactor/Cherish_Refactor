package com.example.cherish_refactor.ui.setting

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentUserModifyBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.util.MyKeyStore


class UserModifyFragment : BaseFragment<FragmentUserModifyBinding>(R.layout.fragment_user_modify) {

    private val viewModel: SettingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel

        setListener()


        return binding.root
    }

    fun setView(){


    }


    fun setListener(){
        binding.settingModifyBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonNickchange.setOnClickListener {
            viewModel.requestNickNameChange(MyKeyStore.getUserId()!!)
            findNavController().popBackStack()

        }

        binding.clickView.setOnClickListener {
            //changeProfileImage()
            binding.buttonNickchange.setBackgroundColor(Color.parseColor("#1AD287"))
        }
        binding.settingEditNickname.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                @SuppressLint("ResourceType")
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.buttonNickchange.setBackgroundColor(Color.parseColor("#1AD287"))
                }
            })

        binding.settingmodifycancel.setOnClickListener {
            binding.settingEditNickname.setText("")
        }

    }





}