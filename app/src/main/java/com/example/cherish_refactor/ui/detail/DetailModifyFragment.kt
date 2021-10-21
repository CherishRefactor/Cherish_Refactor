package com.example.cherish_refactor.ui.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentDetailModifyPlantBinding
import com.example.cherish_refactor.ui.base.BaseFragment


class DetailModifyFragment : BaseFragment<FragmentDetailModifyPlantBinding>(R.layout.fragment_detail_modify_plant) {

    private val detailPlantViewModel :DetailPlantViewModel by viewModels()
    private val args by navArgs<DetailModifyFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm=detailPlantViewModel
        setListener()
        requestModifyView()

        return binding.root
    }

    fun requestModifyView(){
        detailPlantViewModel.requestModifyView(args.cherishId)
    }

    fun requestModifyDetail(){
        detailPlantViewModel.requestModifyDetail(args.cherishId)
    }

    fun setListener() {
        binding.editclock.setOnClickListener {


        }

        binding.editweek.setOnClickListener {



        }
        binding.editBirth.setOnClickListener {

        }
        binding.detailOkBtnModify.setOnClickListener {
            //수정 버튼을 눌렀을 때
            requestModifyDetail()

        }
    }



    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.getItem(0).isVisible = false //disable menuitem 5
        menu.getItem(1).isVisible = false // invisible menuitem 2
        menu.getItem(2).isVisible = true // invisible menuitem 2
        //(activity as DetailPlantActivity).invalidateOptionsMenu()

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true

            }
            R.id.trash -> {
                val deletedialog =
                    /*DeletePlantDialogFragment(
                        R.layout.fragment_delete_plant_dialog,
                        modifycherish
                    ).show(
                        parentFragmentManager, "asdf"
                    )*/
                return true

            }

        }

        return super.onOptionsItemSelected(item)
    }

}