package com.example.cherish_refactor.ui.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentDetailPlantBinding
import com.example.cherish_refactor.ui.base.BaseFragment


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
                R.id.calendar ->{
                    // 캘린더로 넘어
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.calendar ->{
                // 캘린더로 넘어
                true

            }
            else -> return super.onOptionsItemSelected(item)
        }
    }



    override fun onPrepareOptionsMenu(menu: Menu) {
        binding.tbDetail.menu.findItem(R.id.trash).isVisible = false

        super.onPrepareOptionsMenu(menu)
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
