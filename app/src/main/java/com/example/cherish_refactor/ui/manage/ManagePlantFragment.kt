package com.example.cherish_refactor.ui.manage


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cherish_refactor.R
import com.example.cherish_refactor.data.source.remote.api.MyPageCherishData
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.databinding.FragmentManagePlantBinding
import com.example.cherish_refactor.ui.base.BaseFragment


/**
 * 식물 보관함 뷰
 */
class ManagePlantFragment : BaseFragment<FragmentManagePlantBinding>(R.layout.fragment_manage_plant) {

    private val manageViewModel: ManageViewModel by viewModels()
    private var tabIndex: Int = 0
    private var isCollapsed: Boolean = true
    var isSearched: Boolean = false
    private val requestData = RetrofitBuilder
    //private lateinit var tabBindingFirst: MyPageCustomTabBinding
    //private lateinit var tabBindingSecond: MyPageCustomTabBinding
    lateinit var data: List<MyPageCherishData>
    private var mypageusername: String = ""
    private var mypageuseremail: String = ""

    var check: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm=manageViewModel

        //tabBindingFirst = MyPageCustomTabBinding.inflate(inflater, container, false)
        //tabBindingSecond = MyPageCustomTabBinding.inflate(inflater, container, false)


        // 예진이 userId , viewModel.userId.value 라고하면 userId 찾을 수 있어요
        //setTabLayout()
        //initializeServerRequest(binding)

        /*binding.myPageAddPlantBtn.setOnClickListener {
            navigatePhoneBook()
        }*/

        /*binding.userTouch.setOnClickListener {
            navigateUserModifyFragment()
        }
        binding.cancelBtn.setOnClickListener {
            binding.searchBox.visibility = View.VISIBLE
            isSearched = false
            binding.cancelBtn.visibility = View.INVISIBLE
            if (!isCollapsed && tabIndex == 1)
                binding.myPageAddPlantBtn.visibility = View.VISIBLE
            (activity as MainActivity).replaceFragment(tabIndex, data, isSearched)
        }
        binding.goToSetting.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.main_fragment_container, UserModifyFragment().apply {
                arguments = Bundle().apply {
                    putString("settingusernickname", mypageusername)
                    putString("settinguseremail", mypageuseremail)

                }
            })
            transaction.addToBackStack(null)
            transaction.commit()
        }*/
        //검색 버튼 눌렀을 때
        /*binding.searchBox.setOnClickListener {
            binding.searchBox.visibility = View.INVISIBLE
            isSearched = true
            binding.cancelBtn.visibility = View.VISIBLE
            val standardBottomSheetBehavior =
                BottomSheetBehavior.from(binding.homeStandardBottomSheetMypage)
            standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            if (tabIndex == 1) {
                binding.myPageAddPlantBtn.visibility = View.INVISIBLE
                check = true

            }
            (activity as MainActivity).replaceFragment(tabIndex, data, isSearched)

            check = true

        }*/

        /*initializeBottomSheetBehavior(binding)
        initializeProfile(binding)

        binding.myPageBg.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.cherish_my_page_bg
            )
        )
*/
        isSearched = false
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isSearched = false
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTabLayout()
        initializeServerRequest(binding)
        initializeBottomSheetBehavior(binding)
        initializeProfile(binding)
        binding.myPageBg.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.cherish_my_page_bg
            )
        )

        isSearched = false
    }*/


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == 1)
            return true
        return super.onOptionsItemSelected(item)
    }

   /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
    }

    override fun onResume() {
        super.onResume()
        setTabLayout()
        initializeServerRequest(binding)
        initializeBottomSheetBehavior(binding)
        initializeProfile(binding)

        binding.myPageBg.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.cherish_my_page_bg
            )
        )
    }
*/
    /*private fun initializeProfile(binding: FragmentManagePlantBinding) {
        if (ImageSharedPreferences.getGalleryFile(requireContext()).isNotEmpty()) { //앨범일 때
            val uri = Uri.parse(ImageSharedPreferences.getGalleryFile(requireContext()))
            Glide.with(requireContext()).load(uri).circleCrop().into(binding.myPageUserImg)
        } else {
            binding.myPageUserImg.setBackgroundResource(R.drawable.user_img)
        }
    }


    private fun navigateUserModifyFragment() {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment_container, UserModifyFragment().apply {
            arguments = Bundle().apply {
                putString("settingusernickname", mypageusername)
                putString("settinguseremail", mypageuseremail)

            }
        })
        transaction.addToBackStack(null)
        transaction.commit()
    }*/

    /*private fun initializeBottomSheetBehavior(binding: FragmentManagePlantBinding) {
        val standardBottomSheetBehavior =
            BottomSheetBehavior.from(binding.homeStandardBottomSheetMypage)
        // bottom sheet state 지정
        standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        val metrics = resources.displayMetrics

        standardBottomSheetBehavior.peekHeight = (metrics.heightPixels - 76.dp) / 2
        standardBottomSheetBehavior.expandedOffset = 48.dp
        standardBottomSheetBehavior.isHideable = false
        standardBottomSheetBehavior.isGestureInsetBottomIgnored = true

        binding.myPageBg.setBackgroundColor(
            ContextCompat.getColor(
                binding.root.context,
                R.color.cherish_my_page_bg
            )
        )


        standardBottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                binding.myPageBg.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.cherish_my_page_bg
                    )
                )

                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    binding.myPageBg.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_my_page_bg
                        )
                    )
                }

                if (tabIndex == 0) { //식물 탭 클릭 시

                    if (newState == BottomSheetBehavior.STATE_EXPANDED) { //바텀시트 확장됐을 경우
                        binding.myPageBg.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.white
                            )
                        )
                        isSearched = (activity as MainActivity).getIsSearched()
                        binding.myPageAddPlantBtn.visibility = View.VISIBLE //식물 추가 visible
                        isCollapsed = false
                    } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        binding.myPageBg.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.cherish_my_page_bg
                            )
                        )
                    } else if (newState == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                        binding.myPageBg.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.cherish_my_page_bg
                            )
                        )
                        binding.searchBox.visibility = View.VISIBLE
                        binding.cancelBtn.visibility = View.INVISIBLE
                        binding.myPageAddPlantBtn.visibility = View.INVISIBLE //식물 추가 invisible
                        isCollapsed = true
                        isSearched = false
                    } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) { //바텀시트 축소됐을 경우
                        binding.myPageBg.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.cherish_my_page_bg
                            )
                        )
                        binding.searchBox.visibility = View.VISIBLE
                        binding.cancelBtn.visibility = View.INVISIBLE
                        binding.myPageAddPlantBtn.visibility = View.INVISIBLE //식물 추가 invisible
                        isCollapsed = true
                        isSearched = false
                    }

                }
                if (tabIndex == 1) { //연락처 탭 클릭 시
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) { //바텀시트 확장됐을 경우
                        binding.myPageBg.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.white
                            )
                        )

                        isSearched = (activity as MainActivity).getIsSearched()
                        if (isSearched) {
                            binding.myPageAddPlantBtn.visibility = View.INVISIBLE
                        } else {
                            binding.myPageAddPlantBtn.visibility = View.VISIBLE
                        }
                        isCollapsed = false
                    } else if (newState == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                        binding.myPageBg.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.cherish_my_page_bg
                            )
                        )
                        binding.searchBox.visibility = View.VISIBLE
                        binding.cancelBtn.visibility = View.INVISIBLE
                        binding.myPageAddPlantBtn.visibility = View.INVISIBLE //식물 추가 invisible
                        isCollapsed = true
                        isSearched = false
                    } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        binding.myPageBg.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.cherish_my_page_bg
                            )
                        )
                        binding.searchBox.visibility = View.VISIBLE

                        binding.cancelBtn.visibility = View.INVISIBLE
                        binding.myPageAddPlantBtn.visibility = View.INVISIBLE
                        isCollapsed = true
                        isSearched = false
                    }

                }
                (activity as MainActivity).replaceFragment(tabIndex, data, isSearched)
            }

        })
    }*/

   /* private fun setTabLayout() {

        if (binding.myPageBottomTab.getTabAt(0) == null) {
            binding.myPageBottomTab.addTab(
                binding.myPageBottomTab.newTab().setCustomView(
                    createTabView(
                        "식물 ",
                        "0"
                    )
                )
            )
        }

        if (binding.myPageBottomTab.getTabAt(1) == null) {
            binding.myPageBottomTab.addTab(
                binding.myPageBottomTab.newTab().setCustomView(
                    createTabView(
                        "연락처 ",
                        "0"
                    )
                )
            )
        }
    }*/

    /*private fun initializeTabLayoutView(
        binding: FragmentManagePlantBinding,
        data: List<MyPageCherishData>
    ) {
        when (tabIndex) {
            0 -> {
                tabBindingFirst.tabName.setTextAppearance(R.style.MyPageTabSelected)
                tabBindingFirst.tabCount.setTextAppearance(R.style.MyPageTabSelected)

                tabBindingSecond.tabName.setTextAppearance(R.style.MyPageTab)
                tabBindingSecond.tabCount.setTextAppearance(R.style.MyPageTab)

                tabBindingFirst.tabName.setTextColor(Color.parseColor("#454545"))
                tabBindingFirst.tabCount.setTextColor(Color.parseColor("#1AD287"))

                requireActivity().supportFragmentManager.beginTransaction()
                    .add(R.id.my_page_bottom_container, PlantFragment(data)).commit()

                for (i in 0 until binding.myPageBottomTab.tabCount) {
                    val tab = (binding.myPageBottomTab.getChildAt(0) as ViewGroup).getChildAt(i)
                    val p = tab.layoutParams as ViewGroup.MarginLayoutParams
                    p.setMargins(24, 0, 0, 0)
                    tab.requestLayout()
                }

                binding.myPageBg.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.cherish_my_page_bg
                    )
                )

                binding.myPageBottomTab.addOnTabSelectedListener(object :
                    TabLayout.OnTabSelectedListener {

                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tabIndex = binding.myPageBottomTab.selectedTabPosition

                        binding.myPageBg.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.cherish_my_page_bg
                            )
                        )

                        if (tabIndex == 0) {
                            tabBindingFirst.tabName.setTextAppearance(R.style.MyPageTabSelected)
                            tabBindingFirst.tabCount.setTextAppearance(R.style.MyPageTabSelected)

                            tabBindingSecond.tabName.setTextAppearance(R.style.MyPageTab)
                            tabBindingSecond.tabCount.setTextAppearance(R.style.MyPageTab)

                            tabBindingFirst.tabName.setTextColor(Color.parseColor("#454545"))
                            tabBindingFirst.tabCount.setTextColor(Color.parseColor("#1AD287"))

                            if (isCollapsed) {
                                binding.myPageBg.setBackgroundColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.cherish_my_page_bg
                                    )
                                )
                                binding.cancelBtn.visibility = View.INVISIBLE
                                binding.myPageAddPlantBtn.visibility = View.INVISIBLE
                                isSearched = false
                            } else {
                                binding.myPageBg.setBackgroundColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.white
                                    )
                                )
                                isSearched = (activity as MainActivity).getIsSearched()
                                binding.myPageAddPlantBtn.visibility = View.VISIBLE
                            }
                            (activity as MainActivity).replaceFragment(tabIndex, data, isSearched)
                        }
                        if (tabIndex == 1) {
                            tabBindingFirst.tabName.setTextAppearance(R.style.MyPageTab)
                            tabBindingFirst.tabCount.setTextAppearance(R.style.MyPageTab)

                            tabBindingSecond.tabName.setTextAppearance(R.style.MyPageTabSelected)
                            tabBindingSecond.tabCount.setTextAppearance(R.style.MyPageTabSelected)

                            tabBindingSecond.tabName.setTextColor(Color.parseColor("#454545"))
                            tabBindingSecond.tabCount.setTextColor(Color.parseColor("#1AD287"))

                            if (isCollapsed) {
                                binding.myPageBg.setBackgroundColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.cherish_my_page_bg
                                    )
                                )
                                binding.cancelBtn.visibility = View.INVISIBLE

                                isSearched = false

                            } else {
                                binding.myPageBg.setBackgroundColor(
                                    ContextCompat.getColor(
                                        binding.root.context,
                                        R.color.white
                                    )
                                )
                                isSearched = (activity as MainActivity).getIsSearched()
                                if (isSearched) {

                                    binding.myPageAddPlantBtn.visibility = View.INVISIBLE
                                } else {
                                    binding.myPageAddPlantBtn.visibility = View.VISIBLE
                                }
                            }
                            (activity as MainActivity).replaceFragment(tabIndex, data, isSearched)
                        }
                    }
                })

            }
        }
    }*/

    /*private fun createTabView(name: String, count: String?): LinearLayout {
        return when (name) {
            "식물 " -> {
                tabBindingFirst.tabName.text = name
                tabBindingFirst.tabCount.text = count

                tabBindingFirst.root
            }
            else -> {
                tabBindingSecond.tabName.text = name
                tabBindingSecond.tabCount.text = count

                tabBindingSecond.root
            }
        }

    }*/


   /* private fun navigatePhoneBook() {
        val intent = Intent(context, EnrollmentPhoneActivity::class.java)
        intent.putExtra("userId", viewModel.cherishUserId.value!!)
        startActivity(intent)
    }*/

    /*private fun initializeServerRequest(binding: FragmentManagePlantBinding) {
        requestData.myPageAPI.fetchUserPage(viewModel.cherishUserId.value!!)
            .enqueue(
                object : Callback<MyPageUserRes> {
                    override fun onFailure(call: Call<MyPageUserRes>, t: Throwable) {
                        Log.d("통신 실패", t.toString())
                    }

                    override fun onResponse(
                        call: Call<MyPageUserRes>,
                        response: Response<MyPageUserRes>
                    ) {
                        response.takeIf {
                            it.isSuccessful
                        }?.body()
                            ?.let { it ->

                                binding.myPageWateringCnt.text =
                                    it.myPageUserData.waterCount.toString()
                                binding.myPagePostponeCnt.text =
                                    it.myPageUserData.postponeCount.toString()
                                binding.myPageFinishCnt.text =
                                    it.myPageUserData.completeCount.toString()
                                binding.myPageUserName.text = it.myPageUserData.user_nickname
                                mypageusername = it.myPageUserData.user_nickname
                                mypageuseremail = it.myPageUserData.email
                                binding.myPageBottomTab.getTabAt(0)!!.customView = createTabView(
                                    "식물 ",
                                    it.myPageUserData.totalCherish.toString()
                                )
                                binding.myPageBottomTab.getTabAt(1)!!.customView = createTabView(
                                    "연락처 ",
                                    arguments?.getString("phonecount")
                                )

                                initializeTabLayoutView(
                                    binding,
                                    it.myPageUserData.result
                                )
                                data = it.myPageUserData.result
                            }
                    }
                })
    }*/



}