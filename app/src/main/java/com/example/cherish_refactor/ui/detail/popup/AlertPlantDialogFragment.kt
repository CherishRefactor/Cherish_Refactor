package com.example.cherish_refactor.ui.detail.popup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.cherish_refactor.R
import com.example.cherish_refactor.data.source.remote.api.DetailPopUpResponse
import com.example.cherish_refactor.databinding.FragmentAlertPlantDialogBinding
import com.example.cherish_refactor.ui.detail.DetailPlantViewModel
import com.example.cherish_refactor.ui.detail.popup.adapter.DialogAdapter
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle


class AlertPlantDialogFragment: DialogFragment(){

    private val adapter: DialogAdapter by lazy{DialogAdapter()}
    private lateinit var binding: FragmentAlertPlantDialogBinding

    private val viewModel:DetailPlantViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alert_plant_dialog, container, false)
        //val view = inflater.inflate(R.layout.fragment_alert_plant_dialog, container, false)
        //binding = FragmentAlertPlantDialogBinding.bind(view)


        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.vm=viewModel
        binding.lifecycleOwner=viewLifecycleOwner

       //val indicatorView = findViewById<IndicatorView>(R.id.indicator_view)
        //val viewpager = view.findViewById<ViewPager2>(R.id.dialog_viewpager)
        binding.indicatorView.apply {
            setSliderColor(Color.parseColor("#c4c4c4"), Color.parseColor("#31d693"))
            setSliderWidth(resources.getDimension(R.dimen.margin_7dp))
            setSliderHeight(resources.getDimension(R.dimen.margin_7dp))
            setSlideMode(IndicatorSlideMode.NORMAL)
            setIndicatorStyle(IndicatorStyle.CIRCLE)
            setPageSize(4)
            setupWithViewPager(binding.dialogViewpager)
            //notifyDataChanged()
        }
        binding.dialogViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.indicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.indicatorView.onPageSelected(position)
            }
        })

        requestPopUp()
        //initializeIndicatorView(binding)
        observers()
        setListener()

        return binding.root

    }

    fun observers(){
        viewModel.plantPopUp.observe(viewLifecycleOwner){

            var detail= arrayListOf<DetailPopUpResponse.Data.Plant>()
            detail.add(it.data.plantResponse[0])
            var i =1
            it.data.plantDetail.forEach {
                detail.add(DetailPopUpResponse.Data.Plant(it.description,it.level_name,it.image_url,"${i}단계"))
                i+=1
            }
            adapter.setDetail(detail)
        }
    }
    fun requestPopUp(){

        viewModel.requestPopUP(viewModel._plantId.value!!)
        setAdapter()

    }
    fun setListener(){
        binding.cancelBtn.setOnClickListener {
            dismiss()
        }

    }

    private fun setAdapter() {
        binding.dialogViewpager.adapter = adapter

    }

    private fun initializeIndicatorView(binding: FragmentAlertPlantDialogBinding) {

       binding.dialogViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
           override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
               super.onPageScrolled(position, positionOffset, positionOffsetPixels)
               binding.indicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels)
           }

           override fun onPageSelected(position: Int) {
               super.onPageSelected(position)
               binding.indicatorView.onPageSelected(position)
           }
       })
    }
}
