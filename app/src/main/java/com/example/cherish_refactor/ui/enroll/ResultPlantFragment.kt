package com.example.cherish_refactor.ui.enroll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentResultPlantBinding
import com.example.cherish_refactor.ui.base.BaseFragment


class ResultPlantFragment : BaseFragment<FragmentResultPlantBinding>(R.layout.fragment_result_plant) {


    private val viewModel: EnrollmentViewModel by viewModels()
    private val args by navArgs<ResultPlantFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm=viewModel
        requestResult()
        setListener()
        return binding.root
    }

    private fun requestResult(){
        viewModel.requestEnrollPlant(args.resultPlant!!)
    }

    private fun setListener(){

        binding.startbtn.setOnClickListener {
            activity?.finish()
            //findNavController().navigate(R.id.action_resultPlantFragment_to_main_home)
        }
    }
}