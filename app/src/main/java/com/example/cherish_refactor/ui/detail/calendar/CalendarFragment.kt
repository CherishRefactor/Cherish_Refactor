package com.example.cherish_refactor.ui.detail.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentCalendarBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.util.DateUtil


class CalendarFragment : BaseFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {

    private val viewModel: CalendarViewModel by viewModels()
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

    fun requestCalendarView(){
        viewModel.requestCalendar(args.cherishId!!)
    }

    fun setListener(){
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            Log.d("date",date.toString())

            viewModel.calendarData.value?.waterData?.calendarData?.forEach {

                if(it.wateredDate.day ==date.day){
                    val day = viewModel.calendarData.value?.waterData!!.calendarData.indexOf(it)

                    viewModel.selectedDay.value= viewModel.calendarData.value!!.waterData.calendarData[day]

                }
            }

        }

    }

    fun observer(){
        viewModel.calendarData.observe(viewLifecycleOwner){
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