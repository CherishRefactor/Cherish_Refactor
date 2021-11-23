package com.example.cherish_refactor.util.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.DialogDeletePlantBinding
import com.example.cherish_refactor.ui.detail.DetailPlantViewModel

class DeletePlantDialog(cherishId : Int) : DialogFragment() {

    private val viewModel: DetailPlantViewModel by viewModels()

    private lateinit var binding: DialogDeletePlantBinding

    var cherishId=cherishId

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.dialog_delete_plant, container, false)
        binding = DialogDeletePlantBinding.bind(view)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setListener()
        return binding.root


    }

    private fun setListener(){
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }

        binding.buttonDeletePlant.setOnClickListener {

            viewModel.requestPlantDelete(cherishId)
            dismiss()
            findNavController().navigate(R.id.action_detailModifyFragment_to_main_home)

        }
    }
}