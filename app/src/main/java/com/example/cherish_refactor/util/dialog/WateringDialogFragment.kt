package com.example.cherish_refactor.util.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.DialogWateringBinding


class WateringDialogFragment : DialogFragment() {

    //private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.dialog_watering, container, false)
        val binding= DialogWateringBinding.bind(view)
        binding.dialogWatering=this
        //binding.mainViewModel = viewModel
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setListener()
        return binding.root
    }


    fun setListener(){



    }

    fun navigateContact() {
        parentFragmentManager.let { fm -> ContactDialogFragment().show(fm, TAG) }
        dismiss()
    }
/*
    fun navigateNextTimeContact() {
        DelayWateringDialogFragment().show(parentFragmentManager, TAG)
        dismiss()
    }*/


    companion object {
        const val TAG = "WateringDialog"
    }

}