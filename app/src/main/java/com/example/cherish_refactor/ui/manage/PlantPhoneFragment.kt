package com.example.cherish_refactor.ui.manage

import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentPlantPhoneBinding
import com.example.cherish_refactor.domain.entity.Phone
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.enroll.adapter.PhoneBookAdapter
import com.example.cherish_refactor.ui.home.HomeViewModel


class PlantPhoneFragment(view:Boolean) : BaseFragment<FragmentPlantPhoneBinding>(R.layout.fragment_plant_phone) {

    //private val plantAdapter: PlantAdapter by lazy{ PlantAdapter() }
    val phoneBookAdapter: PhoneBookAdapter by lazy { PhoneBookAdapter(radio) }

    var radio=view


    private val manageViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)

        binding.vm=manageViewModel
        //manageViewModel.requestManage(MyKeyStore.getUserId()!!)



        //binding.rvPlantPhone.isNestedScrollingEnabled = true
        //observer()
        setAdapter(radio)
        setSearch()
        setListener()
        return binding.root
    }
    fun setSearch(){
        if(radio==true){
            binding.searchBg.isVisible=true
            binding.myPageAddPhoneBtn.isVisible=true
            //android:scrollbarStyle="outsideOverlay"
            binding.rvPlantPhone.isFocusableInTouchMode=false

        }else{

        }
    }
    fun observer(){
        manageViewModel.isRadio.observe(viewLifecycleOwner){
            setAdapter(it)
        }
    }

    fun setListener(){
        phoneBookAdapter.setItemClickListener(object : PhoneBookAdapter.ItemClickListener{
            override fun onchange(radio: Boolean) {
                if (radio) {
                    binding.myPageAddPhoneBtn.setBackgroundColor(Color.parseColor("#1AD287"))
                    binding.myPageAddPhoneBtn.setTextColor(Color.parseColor("#ffffff"))
                }
            }

        }
        )
        binding.myPageAddPhoneBtn.setOnClickListener {
            findNavController().navigate(
                ManagePlantFragmentDirections.actionMainManagePlantToEnrollPlantFragment(
                    phoneBookAdapter.phonename,
                    phoneBookAdapter.phonenumber
                )
            )
        }

        binding.editSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var searchText = s.toString()
                phoneBookAdapter.setItem(getPhoneNumbers(searchText))
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

    }
    private fun getPhoneNumbers(search:String): List<Phone> {
        val contactList: MutableList<Phone> = ArrayList()
        val projections = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        var where =
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE ?" + " OR " + ContactsContract.CommonDataKinds.Phone.NUMBER + " LIKE ?"

        val contacts = context?.contentResolver?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projections,
            where,
            arrayOf("%$search%"),
            null
        )
        while (contacts!!.moveToNext()){
            val name = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val obj = Phone("",name,number)

            contactList.add(obj)
        }

        var where2 =
            ContactsContract.CommonDataKinds.Phone.NUMBER  + " LIKE ?" + " OR " + ContactsContract.CommonDataKinds.Phone.NUMBER + " LIKE ?"

        val contacts2 = context?.contentResolver?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projections,
            where2,
            arrayOf("%$search%"),
            null
        )
        while (contacts2!!.moveToNext()){
            val name = contacts2.getString(contacts2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number = contacts2.getString(contacts2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val obj = Phone("",name,number)

            contactList.add(obj)
        }
        contacts.close()

        //setStringArrayPref(this,"contact",contactList)
        return contactList.distinct()
    }


    fun setAdapter(view:Boolean){
        binding.rvPlantPhone.layoutManager= LinearLayoutManager(activity)
        binding.rvPlantPhone.adapter=phoneBookAdapter
        phoneBookAdapter.setItem(getPhoneNumbers())

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