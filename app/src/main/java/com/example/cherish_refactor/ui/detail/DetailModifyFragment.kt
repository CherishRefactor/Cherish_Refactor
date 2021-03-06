package com.example.cherish_refactor.ui.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentDetailModifyPlantBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.util.dialog.DeletePlantDialog


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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbDetailModify.inflateMenu(R.menu.toolbar_menu)
        binding.tbDetailModify.menu.findItem(R.id.calendar).isVisible = false
        binding.tbDetailModify.menu.findItem(R.id.setting).isVisible = false
        binding.tbDetailModify.isHapticFeedbackEnabled
        binding.tbDetailModify.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.home ->{
                    findNavController().popBackStack()
                    true
                }
                R.id.trash ->{
                    DeletePlantDialog(args.cherishId).show(parentFragmentManager,"delete")

                    true
                }
                else -> false
            }

        }

    }

    fun requestModifyView(){
        detailPlantViewModel.requestModifyView(args.cherishId)
    }

    fun requestModifyDetail(){
        detailPlantViewModel.requestModifyDetail(args.cherishId)
    }

    fun setListener() {
        binding.imbDetailModifyBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.editclock.setOnClickListener {


        }

        binding.editweek.setOnClickListener {



        }
        binding.editBirth.setOnClickListener {

        }
        binding.detailOkBtnModify.setOnClickListener {
            //?????? ????????? ????????? ???
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