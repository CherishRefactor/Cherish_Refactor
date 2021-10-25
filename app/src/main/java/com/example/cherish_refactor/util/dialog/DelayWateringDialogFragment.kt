package com.example.cherish_refactor.util.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.DialogDelayWateringBinding
import com.example.cherish_refactor.ui.home.HomeViewModel


class DelayWateringDialogFragment : DialogFragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: DialogDelayWateringBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_delay_watering, container, false)
        binding.vm = viewModel
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        initializeNumberPicker(binding)
        sendDelayDayToServer(binding)
        return binding.root
    }

    private fun initializeNumberPicker(binding: DialogDelayWateringBinding) {
        binding.delayWateringDayPicker.apply {
            wrapSelectorWheel = false
            maxValue = 7
            minValue = 1
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        }
    }

    private fun sendDelayDayToServer(binding: DialogDelayWateringBinding) {
        /*binding.delayWateringAcceptBtn.setOnClickListener {
            viewModel.postponeWateringDate(
                PostponeWateringDateReq(
                    viewModel.selectedCherishUser.value!!.id,
                    binding.delayWateringDayPicker.value,
                    true
                )
            )
            viewModel.isWatered.value = false
            shortToast(requireContext(), "미루기 성공!")
            dismiss()
        }*/
    }
}