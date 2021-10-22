package com.example.cherish_refactor.ui.detail.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentCalendarBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.home.HomeViewModel
import com.example.cherish_refactor.util.DateUtil


class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {

    private val viewModel: HomeViewModel by activityViewModels()
    private val args by navArgs<CalendarFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        super.onCreateView(inflater, container, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm=viewModel
        //binding.calendarFragment = this
        requestCalendarView()
        setListener()
        observer()
        return binding.root
    }

    private fun requestCalendarView(){
        viewModel.requestCalendar()
    }

    fun setListener(){
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            Log.d("date",date.toString())

            viewModel.calendarData.value?.waterData?.calendarData?.forEach {

                if(DateUtil.convertDateToCalendarDay(it.wateredDate) ==date){
                    val day = viewModel.calendarData.value?.waterData!!.calendarData.indexOf(it)

                    viewModel.selectedDay.value= viewModel.calendarData.value!!.waterData.calendarData[day]
                    Log.d("ccccccc",viewModel.selectedDay.value.toString())
                }
            }

        }

        binding.calendarViewMemoReviseBtn.setOnClickListener {
            findNavController().navigate(R.id.action_calendarFragment_to_reviewModifyFragment)
        }
        binding.reviewBack.setOnClickListener {
            viewModel._isCalendarChange.value=!viewModel._isCalendarChange.value!!

        }

    }

    fun observer(){
        viewModel.isCalendarChange.observe(viewLifecycleOwner){
            if(it==true){
                binding.calendarView.changeCalendarModeWeeks()
            }else{
                binding.calendarView.changeCalendarModeMonths()
            }
        }
        viewModel.calendarData.observe(viewLifecycleOwner){
            binding.calendarView.selectedDate=DateUtil.convertDateToCalendarDay(it.waterData.calendarData[it.waterData.calendarData.size-1].wateredDate)
            viewModel.calendarData.value?.waterData?.calendarData?.forEach {

                if(DateUtil.convertDateToCalendarDay(it.wateredDate) ==binding.calendarView.selectedDate){
                    val day = viewModel.calendarData.value?.waterData!!.calendarData.indexOf(it)

                    viewModel.selectedDay.value= viewModel.calendarData.value!!.waterData.calendarData[day]
                    Log.d("ccccccc",viewModel.selectedDay.value.toString())
                }
            }

            it.waterData.calendarData.forEach {
                binding.calendarView.addDecorator(
                    DotDecorator(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.cherish_green_sub
                        ), DateUtil.convertDateToCalendarDay(it.wateredDate)
                    )
                )
            }
            binding.calendarView.addDecorator(
                DotDecorator(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.cherish_pink_sub
                    ), DateUtil.convertDateToCalendarDay(it.waterData.futureWaterDate)
                )
            )

        }
    }






}