package com.example.cherish_refactor.ui.enroll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentResultPlantBinding
import com.example.cherish_refactor.ui.base.BaseFragment


class ResultPlantFragment : BaseFragment<FragmentResultPlantBinding>(R.layout.fragment_result_plant) {


    private val viewModel: ResultPlantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm=viewModel


        return binding.root
    }

/*
    fun setResultView() {
        binding.plantExplanation.text = arguments?.getString("plant_explanation")

        if (arguments?.getString("plant_modify")?.contains("\n") == true) {
            binding.textViewModify.text = arguments?.getString("plant_modify")?.split("\n")?.get(0)
            binding.textViewModifyUnder.text =
                arguments?.getString("plant_modify")?.split("\n")?.get(
                    1
                )?.toString()

        }

        val urlstring = arguments?.getString("plant_url")
        Glide.with(this).load(urlstring.toString()).into(binding.imageViewUrl)

        val plant_id_btn = arguments?.getInt("plant_id")

        when (plant_id_btn) {
            1 -> {
                binding.startbtn.setBackgroundColor(Color.parseColor("#F1B0BC"))
                binding.textViewFlower.setTextColor(Color.parseColor("#F1B0BC"))
                binding.viewFlower.setBackgroundColor(Color.parseColor("#F1B0BC"))
                binding.tipBox.setBackgroundResource(R.drawable.plant_tip_box)
                binding.textViewflowerMean.setTextColor(Color.parseColor("#F1B0BC"))
            }
            2 -> {
                binding.startbtn.setBackgroundColor(Color.parseColor("#AAB3D1"))
                binding.textViewFlower.setTextColor(Color.parseColor("#AAB3D1"))
                binding.viewFlower.setBackgroundColor(Color.parseColor("#AAB3D1"))
                binding.tipBox.setBackgroundResource(R.drawable.plant_tip_box2)
                binding.textViewflowerMean.setTextColor(Color.parseColor("#AAB3D1"))

            }
            3 -> {
                binding.startbtn.setBackgroundColor(Color.parseColor("#97CDBD"))
                binding.textViewFlower.setTextColor(Color.parseColor("#97CDBD"))
                binding.viewFlower.setBackgroundColor(Color.parseColor("#97CDBD"))
                binding.tipBox.setBackgroundResource(R.drawable.plant_tip_box3)
                binding.textViewflowerMean.setTextColor(Color.parseColor("#97CDBD"))

            }
            4 -> {
                binding.startbtn.setBackgroundColor(Color.parseColor("#9AB7DE"))
                binding.textViewFlower.setTextColor(Color.parseColor("#9AB7DE"))
                binding.viewFlower.setBackgroundColor(Color.parseColor("#9AB7DE"))
                binding.tipBox.setBackgroundResource(R.drawable.plant_tip_box4)
                binding.textViewflowerMean.setTextColor(Color.parseColor("#9AB7DE"))
            }
            5 -> {
                binding.startbtn.setBackgroundColor(Color.parseColor("#9EC8EC"))
                binding.textViewFlower.setTextColor(Color.parseColor("#9EC8EC"))
                binding.viewFlower.setBackgroundColor(Color.parseColor("#9EC8EC"))
                binding.tipBox.setBackgroundResource(R.drawable.plant_tip_box5)
                binding.textViewflowerMean.setTextColor(Color.parseColor("#9EC8EC"))
            }
        }

        binding.textViewflowerMean.text = arguments?.getString("plant_mean")


    }*/

  /*  fun setListeners() {
        binding.startbtn.setOnClickListener {
            // LoadingDialog를 보여주면 됨
            val intent = Intent(requireContext(), MainActivity::class.java)

            if (activity?.intent?.getIntExtra("codeFirstStart", -1) == 1) {
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.putExtra("userId", viewModel.userId)
                intent.putExtra("userNickname", viewModel.userNickname)
                startActivity(intent)
            } else {
                activity?.finish()


            }
        }
    }*/




}