package com.example.cherish_refactor.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentHomeBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.home.adapter.HomeCherryListAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home){

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var standardBottomSheetBehavior: BottomSheetBehavior<View>
    private val homeCherryListAdapter by lazy{ HomeCherryListAdapter()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel
        standardBottomSheetBehavior = BottomSheetBehavior.from(binding.homeStandardBottomSheet)
        getCherishItem()
        setListener()
        observer()
        setAdapter()
        setBottom()
        addBottomSheetCallback()
        return binding.root
    }

    private fun setBottom(){
        standardBottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_COLLAPSED
            peekHeight = 160
        }
    }

    private fun addBottomSheetCallback() {
        standardBottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                binding.homeModalAnimateView.alpha = (slideOffset - 0.2f)
                val lowerSlide = 0.3
                val middleSlide = 0.5
                if (slideOffset < lowerSlide) {
                    standardBottomSheetBehavior.peekHeight = 60
                } else if (slideOffset <= middleSlide) {
                    standardBottomSheetBehavior.setPeekHeight(160,
                        true
                    )
                }
            }
        })
    }

    private fun setAdapter(){
        binding.homeUserList.adapter=homeCherryListAdapter
        binding.homeUserList.layoutManager = GridLayoutManager(context, 5)

    }


    private fun getCherishItem(){
        viewModel.requestMainCherishItem(609)

    }
    private fun observer(){
        viewModel.user.observe(viewLifecycleOwner){
            homeCherryListAdapter.setItem(it)
        }

    }



    private fun setListener(){
        homeCherryListAdapter.onClick = { user ,position->
            Log.d("asdf",user.toString())
            viewModel.setSelectedUser(user)
        }
        binding.homeMovePlantDetail.setOnClickListener {
            /*navigateDetailPlant(
                //viewModel.cherishUserId.value!!
                args.userId
            )*/

            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToDetailPlantFragment(viewModel.selectedCherishId.value!!))
        }
        binding.homeWateringBtn.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_detailPlantFragment)
        }
        binding.homeUserAddText.setOnClickListener {
            findNavController().navigate(R.id.action_main_home_to_phoneBookFragment)
        }
    }

    /*fun setListener(){
        binding.homeWateringBtn.setOnClickListener {
            navigateWatering()
        }

        binding.homeUserAddText.setOnClickListener {
            navigatePhoneBook()
        }

        binding.homeMovePlantDetail.setOnClickListener {
            navigateDetailPlant(
                //viewModel.cherishUserId.value!!
                args.userId
            )
        }

    }*/

   /* override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val receivedMessage = args.userId

    }*/




    /*override fun onItemClick(itemBinding: MainCherryItemBinding, position: Int) {
        viewModel.selectedCherishUser.value = homeCherryListAdapter.data[position]
        homeCherryListAdapter.data[0] = homeCherryListAdapter.data[position]
        viewModel.cherishSelectedPosition.value = position
        homeCherryListAdapter.notifyItemChanged(0)
        slideDownBottomSheet()
    }*/

  /*  private fun initializeRecyclerView(
        homeCherryListAdapter: HomeCherryListAdapter
    ) {
        binding.homeUserList.apply {
            adapter = homeCherryListAdapter
            layoutManager = GridLayoutManager(context, 5)
            addItemDecoration(GridItemDecorator(spanCount = 5, spacing = 6.dp, includeEdge = true))
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }
*/

    // 화면이동
   /* private fun navigateWatering() {
        if (viewModel.selectedCherishUser.value?.dDay!! <= 0) {
            WateringDialogFragment().show(parentFragmentManager, TAG)
        } else {
            longToast(requireContext(), "물 줄수있는 날이 아니에요 ㅠ")
        }
    }*/

    /*private fun navigatePhoneBook() {
        val intent = Intent(context, EnrollmentPhoneActivity::class.java)
        intent.putExtra("userId", viewModel.cherishUserId.value)
        startActivity(intent)
    }*/

    /*private fun navigateDetailPlant(userId: Int?) {
        val intent = Intent(activity, DetailPlantActivity::class.java)
        intent.putExtra("userId", userId)
        intent.putExtra("cherishId", viewModel.selectedCherishUser.value?.id)
        intent.putExtra("cherishUserPhoneNumber", viewModel.selectedCherishUser.value?.phoneNumber)
        intent.putExtra("cherishNickname", viewModel.selectedCherishUser.value?.nickName)
        intent.putExtra("userNickname", viewModel.userNickName.value)
        intent.putExtra("cherishuserId", viewModel.cherishUserId.value)
        intent.putExtra("selectedUserDday", viewModel.selectedCherishUser.value!!.dDay)
        startActivityForResult(intent, CODE_MOVE_DETAIL_PLANT)
    }*/



    companion object {
        private const val TAG = "HomeFragment"
        private const val CODE_MOVE_DETAIL_PLANT = 1005
    }

}