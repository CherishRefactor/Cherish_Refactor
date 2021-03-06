package com.example.cherish_refactor.ui.manage


import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentManagePlantBinding
import com.example.cherish_refactor.databinding.MyPageCustomTabBinding
import com.example.cherish_refactor.domain.entity.Phone
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.home.HomeViewModel
import com.example.cherish_refactor.ui.manage.adapter.ManageViewPagerAdapter
import com.example.cherish_refactor.util.MyKeyStore
import com.example.cherish_refactor.util.PixelUtil.dp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator


class ManagePlantFragment : BaseFragment<FragmentManagePlantBinding>(R.layout.fragment_manage_plant) {

    private val manageViewModel: HomeViewModel by activityViewModels()
    private lateinit var tabBindingFirst: MyPageCustomTabBinding
    private lateinit var tabBindingSecond: MyPageCustomTabBinding

   private var myPageBottomSheetAdapter: ManageViewPagerAdapter? =null
    private lateinit var standardBottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm=manageViewModel
        tabBindingFirst = MyPageCustomTabBinding.inflate(inflater, container, false)
        tabBindingSecond = MyPageCustomTabBinding.inflate(inflater, container, false)
        standardBottomSheetBehavior = BottomSheetBehavior.from(binding.homeStandardBottomSheetMypage)

        myPageBottomSheetAdapter=ManageViewPagerAdapter(requireActivity())

        addBottomSheetCallback()
        initializeBottomSheetBehavior(binding)

        requestMange()
        setListener()
        setTab()
        return binding.root
    }
    /*override fun onPause() {
        super.onPause()
        binding.myPageViewpager.adapter = null
    }*/

    fun observer(){

    }
    private fun addBottomSheetCallback() {
        standardBottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                /*if (newState == BottomSheetBehavior.STATE_SETTLING) {
                    standardBottomSheetBehavior.maxWidth=
                    standardBottomSheetBehavior.peekHeight=210
                }*/
                if(BottomSheetBehavior.STATE_EXPANDED==newState){
                    binding.myPageAddPlantBtn.isVisible=true
                    manageViewModel.showRadio()
                    manageViewModel.isManageBg.value=true
                    if(binding.myPageBottomTab.getTabAt(1)?.isSelected==true) {
                        binding.myPageViewpager.currentItem=1

                    }



                }else{
                    manageViewModel.isManageBg.value=false
                    binding.myPageAddPlantBtn.isVisible=false
                    manageViewModel.noShowRadio()
                    myPageBottomSheetAdapter?.setRadioVisi(false)
                    myPageBottomSheetAdapter?.setSearchVisi(false)
                    binding.myPageViewpager.adapter=myPageBottomSheetAdapter
                }

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }
    fun getContact():Int{
        val contactList: MutableList<Phone> = ArrayList()

        val contacts = context?.contentResolver?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        while (contacts!!.moveToNext()){
            val name = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val obj = Phone("",name,number)

            contactList.add(obj)
        }
        contacts.close()

        //setStringArrayPref(this,"contact",contactList)
        return contactList.size
       // Toast.makeText(context, "????????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        setAdapter()
    }



    fun setAdapter(){

        //binding.myPageViewpager.requestDisallowInterceptTouchEvent(false)
        binding.myPageViewpager.adapter=myPageBottomSheetAdapter




        TabLayoutMediator(binding.myPageBottomTab, binding.myPageViewpager
        ) { tab, position ->
            val tabBinding: ViewDataBinding? = DataBindingUtil.inflate(
                LayoutInflater.from(binding.myPageBottomTab.context),
                R.layout.my_page_custom_tab, binding.myPageBottomTab,
                false
            )
            tab.let {
                it.customView = tabBinding?.root
            }

            if(position==0){
                tab.customView=createTabView(
                    "?????? ",
                    manageViewModel.total.value.toString()
                )
            }else if(position==1){
                tab.customView= createTabView(
                    "????????? ",
                    getContact().toString()
                )

            }

        }.attach()

    }


    private fun createTabView(name: String, count: String?): LinearLayout {
        return when (name) {
            "?????? " -> {
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

    }

    fun setTab(){

        binding.myPageBottomTab.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) { // ?????? X -> ?????? O
                binding.myPageViewpager.currentItem = tab.position

                when(binding.myPageViewpager.currentItem) {
                    0 -> {
                        tabBindingFirst.tabName.setTextAppearance(R.style.MyPageTabSelected)
                        tabBindingFirst.tabCount.setTextAppearance(R.style.MyPageTabSelected)

                        tabBindingSecond.tabName.setTextAppearance(R.style.MyPageTab)
                        tabBindingSecond.tabCount.setTextAppearance(R.style.MyPageTab)

                        tabBindingFirst.tabName.setTextColor(Color.parseColor("#454545"))
                        tabBindingFirst.tabCount.setTextColor(Color.parseColor("#1AD287"))
                    }
                    1->{
                        tabBindingFirst.tabName.setTextAppearance(R.style.MyPageTab)
                        tabBindingFirst.tabCount.setTextAppearance(R.style.MyPageTab)

                        tabBindingSecond.tabName.setTextAppearance(R.style.MyPageTabSelected)
                        tabBindingSecond.tabCount.setTextAppearance(R.style.MyPageTabSelected)

                        tabBindingSecond.tabName.setTextColor(Color.parseColor("#454545"))
                        tabBindingSecond.tabCount.setTextColor(Color.parseColor("#1AD287"))
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) { // ?????? O -> ??????

            }

            override fun onTabReselected(tab: TabLayout.Tab) { // ?????? O -> ?????? O
            }
        })
    }

    fun requestMange(){
        manageViewModel.requestManage(MyKeyStore.getUserId()!!)
    }

    fun setListener(){
        binding.myPageAddPlantBtn.setOnClickListener {
            findNavController().navigate(R.id.action_main_manage_plant_to_phoneBookFragment)

        }

        binding.userTouch.setOnClickListener {
            findNavController().navigate(R.id.action_main_manage_plant_to_userModifyFragment)
        }
        binding.cancelBtn.setOnClickListener {
            binding.searchBox.visibility = View.VISIBLE
            binding.cancelBtn.visibility = View.INVISIBLE

        }
        binding.searchBox.setOnClickListener {


            binding.searchBox.visibility = View.INVISIBLE
            binding.cancelBtn.visibility = View.VISIBLE

            val standardBottomSheetBehavior =
                BottomSheetBehavior.from(binding.homeStandardBottomSheetMypage)
            standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

            myPageBottomSheetAdapter?.setRadioVisi(true)
            myPageBottomSheetAdapter?.setSearchVisi(true)
            binding.myPageViewpager.adapter=myPageBottomSheetAdapter



        }


    }
    private fun initializeBottomSheetBehavior(binding: FragmentManagePlantBinding) {

        // bottom sheet state ??????
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
    }


}