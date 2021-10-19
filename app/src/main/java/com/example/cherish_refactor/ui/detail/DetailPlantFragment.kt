package com.example.cherish_refactor.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentDetailPlantBinding
import com.example.cherish_refactor.ui.base.BaseFragment


class DetailPlantFragment : BaseFragment<FragmentDetailPlantBinding>(R.layout.fragment_detail_plant) {


    companion object {
        private val TAG = "DetailPlantFragment"
    }

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
        setHasOptionsMenu(true)
       // updateToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbDetail.inflateMenu(R.menu.toolbar_menu)
        binding.tbDetail.title = "식물 카드"

        binding.tbDetail.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.calendar ->{
                    // 캘린더로 넘어
                    true

                }
                R.id.setting ->{

                    //cherish id modifyfragment
                    true
                }
                else -> false
            }

        }


        //clearToolbarMenu()
        //updateToolbar()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        binding.tbDetail.menu.findItem(R.id.trash).isVisible = false

        super.onPrepareOptionsMenu(menu)
    }
    fun clearToolbarMenu() {
        binding.tbDetail.menu.clear()
    }

    fun updateToolbar() {

        val saveItem = binding.tbDetail.menu.findItem(R.id.trash)
        binding.tbDetail.menu.getItem(2).isVisible = false

    }

    private fun requestPlantDetail(){
        detailPlantViewModel.requestPlantDetail(args.cherishId)

    }

    private fun setListener(){
        binding.imageViewDetailUrl.setOnClickListener {
        }
        binding.imageButton3detail.setOnClickListener {

        }
        binding.buttonWater.setOnClickListener {


        }

        binding.memocons.setOnClickListener {

        }
        binding.memocons2.setOnClickListener {

        }
    }

}
