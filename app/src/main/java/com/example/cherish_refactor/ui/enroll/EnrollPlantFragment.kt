package com.example.cherish_refactor.ui.enroll

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentEnrollPlantBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.util.dialog.BirthPickerDialog
import com.example.cherish_refactor.util.dialog.ClockPickerDialog
import com.example.cherish_refactor.util.dialog.DatePickerDialog


class EnrollPlantFragment : BaseFragment<FragmentEnrollPlantBinding>(R.layout.fragment_enroll_plant) {

    private lateinit var progressDialog: AppCompatDialog
    private val enrollmentViewModel :EnrollmentViewModel by viewModels()
    private val args by navArgs<EnrollPlantFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm=enrollmentViewModel

        setListener()
        requestEnroll()
        return binding.root
    }

    private fun requestEnroll(){
        enrollmentViewModel.requestnickname(args.name,args.phone)
    }

    private fun setListener(){
        binding.editBirth.setOnClickListener {
            BirthPickerDialog{
                enrollmentViewModel.requestbirth(it)
            }.show(childFragmentManager, "")
        }
        binding.editclock.setOnClickListener {
            ClockPickerDialog{
                enrollmentViewModel.requestclock(it)
            }.show(childFragmentManager, "")
        }
        binding.editweek.setOnClickListener {
            DatePickerDialog{
                enrollmentViewModel.requestdate(it)
            }.show(childFragmentManager, "")
        }
        binding.detailOkBtn.setOnClickListener {
            //enrollmentViewModel.requestEnrollPlant(609)
            progressON()
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(EnrollPlantFragmentDirections.actionEnrollPlantFragmentToResultPlantFragment(enrollmentViewModel.requestResult()))
                progressOFF()
            }, 4000)

        }
        binding.backEnroll.setOnClickListener {
            findNavController().popBackStack()
        }


    }
    fun progressON() {

        progressDialog = AppCompatDialog(context)
        progressDialog.setCancelable(false)
        progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.findViewById<ImageView>(R.id.imageViewProgress)?.let {
            Glide.with(this).load(R.raw.cherish_loading).into(
                it
            )
        }
        progressDialog.show()

    }

    fun progressOFF() {
        if (progressDialog != null && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }





}