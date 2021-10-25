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

        binding.vm=viewModel
        //binding.calendarFragment = this
        requestCalendarView()
        setListener()
        observer()

        return binding.root
    }

    fun setMemoCalendarView(){

    }

    override fun onResume() {
        binding.calendarView.removeDecorators()
        binding.calendarView.clearSelection()
        super.onResume()

    }

    private fun requestCalendarView(){
        viewModel.requestCalendar(args.cherishId!!)
    }

    fun showCalendarMemoFirst(){
        findNavController().navigate(R.id.action_detailPlantFragment_to_calendarFragment)
    }

    fun setListener(){
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            Log.d("date",date.toString())

            /*if(DateUtil.convertDateToCalendarDay(viewModel.calendarData.value?.waterData?.futureWaterDate!!) == date){
                viewModel.selectedNullDay.value=date
            }
*/          viewModel.selectedNullDay.value=date

            viewModel.noShowMemo()
            if(DateUtil.convertDateToCalendarDay(viewModel.calendarData.value?.waterData?.futureWaterDate !!)==date){
                viewModel.noShowMemo()
            }


            viewModel.calendarData.value?.waterData?.calendarData?.forEach {

                if(DateUtil.convertDateToCalendarDay(it.wateredDate) ==date ){
                    viewModel.showMemo()
                    val day = viewModel.calendarData.value?.waterData!!.calendarData.indexOf(it)
                    viewModel.selectedNullDay.value=date
                    viewModel.selectedDay.value= viewModel.calendarData.value!!.waterData.calendarData[day]

                    Log.d("assdfasdf11111",viewModel.selectedDay.value.toString())

                }

                //viewModel.noShowMemo()
            }



        }

        binding.calendarViewMemoReviseBtn.setOnClickListener {
            findNavController().navigate(CalendarFragmentDirections.actionCalendarFragmentToReviewModifyFragment(args.cherishId!!))
        }
        binding.reviewBack.setOnClickListener {
            viewModel._isCalendarChange.value=!viewModel._isCalendarChange.value!!

        }
        binding.btnCalendarBack.setOnClickListener {
            findNavController().popBackStack()
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
            if(args.date != null){ // 메모를 클릭해서 들어왔을 때
                    //val day = args.date.split('-')[1]
                    binding.calendarView.selectedDate =DateUtil.convertStringToCalendarDay(args.date)
                        //DateUtil.convertDateToCalendarDay(it.waterData.calendarData[it.waterData.calendarData.size - 1].wateredDate)
                    viewModel.selectedNullDay.value=DateUtil.convertStringToCalendarDay(args.date)

                    viewModel.calendarData.value?.waterData?.calendarData?.forEach {
                        viewModel.showMemo()
                        if (DateUtil.convertDateToCalendarDay(it.wateredDate) == binding.calendarView.selectedDate) {
                            val day = viewModel.calendarData.value?.waterData!!.calendarData.indexOf(it)

                            viewModel.selectedDay.value =
                                viewModel.calendarData.value!!.waterData.calendarData[day]
                            Log.d("ccccccc", viewModel.selectedDay.value.toString())
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



            }else { /// 값이 없으
                if(it.waterData.calendarData.isEmpty()){
                    binding.calendarView.addDecorator(
                        DotDecorator(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.cherish_pink_sub
                            ), DateUtil.convertDateToCalendarDay(it.waterData.futureWaterDate)
                        )
                    )
                    binding.calendarView.selectedDate= DateUtil.convertDateToCalendarDay(it.waterData.futureWaterDate)
                    viewModel.selectedNullDay.value=DateUtil.convertDateToCalendarDay(it.waterData.futureWaterDate)
                }else {
                    binding.calendarView.selectedDate =
                        DateUtil.convertDateToCalendarDay(it.waterData.calendarData[it.waterData.calendarData.size - 1].wateredDate)
                    viewModel.selectedNullDay.value=DateUtil.convertDateToCalendarDay(it.waterData.calendarData[it.waterData.calendarData.size - 1].wateredDate)

                    viewModel.calendarData.value?.waterData?.calendarData?.forEach {

                        if (DateUtil.convertDateToCalendarDay(it.wateredDate) == binding.calendarView.selectedDate) {
                            val day = viewModel.calendarData.value?.waterData!!.calendarData.indexOf(it)

                            viewModel.selectedDay.value =
                                viewModel.calendarData.value!!.waterData.calendarData[day]
                            Log.d("ccccccc", viewModel.selectedDay.value.toString())
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
    }






}