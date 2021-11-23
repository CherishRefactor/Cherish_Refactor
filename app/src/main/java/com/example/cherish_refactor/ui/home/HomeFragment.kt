package com.example.cherish_refactor.ui.home


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cherish_refactor.MainApplication
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentHomeBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.home.adapter.HomeCherryListAdapter
import com.example.cherish_refactor.util.MyKeyStore
import com.example.cherish_refactor.util.PixelUtil.dp
import com.example.cherish_refactor.util.dialog.WateringDialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home){

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var standardBottomSheetBehavior: BottomSheetBehavior<View>
    private val homeCherryListAdapter by lazy{ HomeCherryListAdapter()}
    val MY_PERMISSION_ACCESS_ALL = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel
        standardBottomSheetBehavior = BottomSheetBehavior.from(binding.homeStandardBottomSheet)

        setListener()

        getCherishItem()
        setAdapter()
        observer()

        setBottom()
        addBottomSheetCallback()



        return binding.root
    }



   /* override fun onResume() {
        getCherishItem()
        setAdapter()
        observer()
        super.onResume()
    }*/

    fun setBottom(){
        standardBottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_COLLAPSED
            isFitToContents = false
            peekHeight = (MainApplication.pixelRatio.screenHeight.dp / 5.dp)
            halfExpandedRatio = 0.23f // 이거 비율만 좀 수정해주면 될듯?
            expandedOffset = 100.dp
            isHideable = false
        }
    }

    private fun slideDownBottomSheet() {
        /*standardBottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_COLLAPSED
            peekHeight = 210.dp
        }*/
        standardBottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_COLLAPSED
            peekHeight = (MainApplication.pixelRatio.screenHeight.dp / 5.dp)
        }
    }

    private fun addBottomSheetCallback() {
        standardBottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                /*if (newState == BottomSheetBehavior.STATE_SETTLING) {
                    standardBottomSheetBehavior.maxWidth=
                    standardBottomSheetBehavior.peekHeight=210
                }*/

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                binding.homeModalAnimateView.alpha = (slideOffset - 0.2f)
                val lowerSlide = 0.3
                val middleSlide = 0.5
                if (slideOffset < lowerSlide) {
                    standardBottomSheetBehavior.peekHeight = 60.dp
                } else if (slideOffset <= middleSlide) {
                    standardBottomSheetBehavior.setPeekHeight(
                        (MainApplication.pixelRatio.screenHeight.dp / 5.dp),
                        true
                    )
                }
            }
        })
    }

    private fun setAdapter(){
        binding.homeUserList.adapter=homeCherryListAdapter
        binding.homeUserList.layoutManager = GridLayoutManager(context, 5)
        binding.homeUserList.isNestedScrollingEnabled = false
        binding.homeUserList.setHasFixedSize(true)

    }


    private fun getCherishItem(){

        viewModel.requestMainCherishItem(MyKeyStore.getUserId()!!)
       // homeCherryListAdapter.data.add(0,viewModel.selectedFirst.value!!)


    }
    private fun observer(){
        viewModel.user.observe(viewLifecycleOwner){
            //homeCherryListAdapter.data[0]=it[0]
            if(it.isNotEmpty()) {
                homeCherryListAdapter.setItem(it)
            }else{
                findNavController().navigate(R.id.action_main_home_to_phoneBookFragment)

            }
        }

    }



    private fun setListener(){
        homeCherryListAdapter.onClick = { user,position ->
            //homeCherryListAdapter.notifyDataSetChanged()
            homeCherryListAdapter.setOp(position)
            //homeCherryListAdapter.seletedPosition=position
            //homeCherryListAdapter.data.clear()
            //homeCherryListAdapter.data.add(0,user)
            //homeCherryListAdapter.setchange(user)
            //homeCherryListAdapter.data.removeAt(0)
            //Log.d("asdf",position.toString())
            viewModel.setSelectedUser(user)
            homeCherryListAdapter.data[0]=user
            //homeCherryListAdapter.data.add(0,user)
            homeCherryListAdapter.notifyItemChanged(0)
            homeCherryListAdapter.notifyItemRangeChanged(0, homeCherryListAdapter.data.size)
            //homeCherryListAdapter.notifyDataSetChanged()

            slideDownBottomSheet()
            // 클릭하면 맨처음에 하나 생겨나야함

        }
        binding.homeMovePlantDetail.setOnClickListener {
            /*navigateDetailPlant(
                //viewModel.cherishUserId.value!!
                args.userId
            )*/

            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToDetailPlantFragment(viewModel.selectedCherishId.value!!))
        }
        binding.homeWateringBtn.setOnClickListener {

                if (viewModel.selectedCherishUser.value?.dDay!! <= 0) {
                    WateringDialogFragment().show(parentFragmentManager, TAG)

                } else {
                    Toast.makeText(context, "물 줄수있는 날이 아니에요!",Toast.LENGTH_SHORT).show()
                }

        }
        binding.homeUserAddText.setOnClickListener {
            findNavController().navigate(R.id.action_main_home_to_phoneBookFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CODE_MOVE_DETAIL_PLANT) {
            if (resultCode == Activity.RESULT_OK) {
                viewModel.isWatered.value = data?.getBooleanExtra("animationTrigger", false)
            }
        }
    }


    companion object {
        private const val TAG = "HomeFragment"
        private const val CODE_MOVE_DETAIL_PLANT = 1005
    }

}