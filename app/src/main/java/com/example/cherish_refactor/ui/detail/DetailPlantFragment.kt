package com.example.cherish_refactor.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentDetailPlantBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.util.dialog.WateringDialogFragment


class DetailPlantFragment : BaseFragment<FragmentDetailPlantBinding>(R.layout.fragment_detail_plant) {

    private val detailPlantViewModel: DetailPlantViewModel by viewModels()
    private val args by navArgs<DetailPlantFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.plantDetailVM=detailPlantViewModel
        requestPlantDetail()
        setListener()
        observer()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbDetail.inflateMenu(R.menu.toolbar_menu)
        binding.tbDetail.menu.findItem(R.id.trash).isVisible = false
        binding.tbDetail.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.home ->{
                    findNavController().popBackStack()
                    true
                }
                R.id.calendar ->{
                    // 캘린더로 넘어
                    findNavController().navigate(DetailPlantFragmentDirections.actionDetailPlantFragmentToCalendarFragment(args.cherishId))
                    true

                }
                R.id.setting ->{

                    findNavController().navigate(DetailPlantFragmentDirections.actionDetailPlantFragmentToDetailModifyFragment(args.cherishId))


                    //cherish id modifyfragment
                    true
                }
                else -> false
            }

        }

    }
   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.calendar ->{
                // 캘린더로 넘어
                findNavController().navigate(R.id.action_detailPlantFragment_to_calendarFragment)
                true

            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
*/


    override fun onPrepareOptionsMenu(menu: Menu) {
        binding.tbDetail.menu.findItem(R.id.trash).isVisible = false

        super.onPrepareOptionsMenu(menu)
    }

    private fun requestPlantDetail(){
        detailPlantViewModel.requestPlantDetail(args.cherishId)

    }
    private fun observer(){
        detailPlantViewModel.gage.observe(viewLifecycleOwner){
            binding.test.progress=it
            if(it<50){
                binding.test.setProgressStartColor(Color.parseColor("#F7596C"))
                binding.test.setProgressEndColor(Color.parseColor("#F7596C"))
            }
        }
        detailPlantViewModel.isEmptyMemo.observe(viewLifecycleOwner){
            if(it==false){


                binding.memocons.setOnClickListener {

                    findNavController().navigate(DetailPlantFragmentDirections.actionDetailPlantFragmentToCalendarFragment(args.cherishId,
                        detailPlantViewModel.plantDetail.value!!.reviews[0].water_date))

                }
                binding.memocons2.setOnClickListener {
                    findNavController().navigate(DetailPlantFragmentDirections.actionDetailPlantFragmentToCalendarFragment(args.cherishId,
                        detailPlantViewModel.plantDetail.value!!.reviews[1].water_date))
                }

            }
        }
    }

    private fun setListener(){
        binding.imageViewDetailUrl.setOnClickListener {
            detailPlantViewModel._isTouch.value=!detailPlantViewModel._isTouch.value!!

        }
        binding.imageButton3detail.setOnClickListener {

        }
        binding.buttonWater.setOnClickListener {
            //detail water
            if (detailPlantViewModel.plantDetail.value?.dDay!! <= 0) {
                WateringDialogFragment().show(parentFragmentManager, "detail")

            } else {
                Toast.makeText(context, "물 줄수있는 날이 아니에요!", Toast.LENGTH_SHORT).show()
            }


        }
        binding.imbDetailBack.setOnClickListener {
            findNavController().popBackStack()
        }


    }

}
