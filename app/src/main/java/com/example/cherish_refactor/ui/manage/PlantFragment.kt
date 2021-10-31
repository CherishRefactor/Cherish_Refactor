package com.example.cherish_refactor.ui.manage

import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cherish_refactor.R
import com.example.cherish_refactor.data.source.remote.api.ManageResponse
import com.example.cherish_refactor.databinding.FragmentPlantBinding
import com.example.cherish_refactor.domain.entity.Phone
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.home.HomeViewModel
import com.example.cherish_refactor.ui.manage.adapter.PlantAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior


class PlantFragment(view:Boolean) : BaseFragment<FragmentPlantBinding>(R.layout.fragment_plant) {

    private val plantAdapter: PlantAdapter by lazy{ PlantAdapter() }
    //private val phoneBookAdapter: PhoneBookAdapter by lazy{ PhoneBookAdapter() }
    private lateinit var standardBottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    var search=view


    private val manageViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)

        var view = LayoutInflater.from(context).inflate(R.layout.fragment_manage_plant,null)
        binding.vm=manageViewModel
        //manageViewModel.requestManage(MyKeyStore.getUserId()!!)
        //standardBottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.home_standardBottomSheet_mypage))

        binding.rvPlant.adapter=plantAdapter
        setAdapter()
        observer()
        setListener()
        setSearch()


        return binding.root
    }
    fun setSearch(){
        if(search==true){
            binding.searchBg.isVisible=true
        }
    }

    fun changeList(){
        val mulist= mutableListOf<ManageResponse.MyPageUserData.MyPageCherishData>()

    }


    fun setListener(){
        binding.editSearch.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    var searchText = s.toString()
                    plantAdapter.setManage(manageViewModel.searchPlant(searchText))
                }
            })
        plantAdapter.setItemClickListener(object :PlantAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                findNavController().navigate(ManagePlantFragmentDirections.actionManagePlantFragmentToDetailPlantFragment(manageViewModel.manageUser.value?.myPageUserData?.result?.get(position)?.id!!))
                //findNavController().navigate(R.id.action_managePlantFragment_to_detailPlantFragment)
            }

        }
        )
    }

    fun setAdapter(){
        binding.rvPlant.layoutManager= LinearLayoutManager(context)
    }

    fun observer(){
        manageViewModel.manageUser.observe(viewLifecycleOwner){
            plantAdapter.setManage(it.myPageUserData.result)
        }
    }

    private fun getPhoneNumbers(): List<Phone> {
        val contactList: MutableList<Phone> = ArrayList()
        val projections = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        val contacts = context?.contentResolver?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projections,
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
        return contactList
    }


}