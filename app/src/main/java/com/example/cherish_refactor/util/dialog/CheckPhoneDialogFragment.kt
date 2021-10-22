package com.example.cherish_refactor.util.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.DialogCheckPhoneBinding


class CheckPhoneDialogFragment : DialogFragment() {

    private lateinit var binding: DialogCheckPhoneBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.dialog_check_phone, container, false)
        binding = DialogCheckPhoneBinding.bind(view)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setListener()
        return binding.root


    }

    fun setListener(){
        binding.buttonClose.setOnClickListener {
            dismiss()
        }

    }
}