package com.example.cherish_refactor.ui.enroll

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cherish_refactor.R
import com.example.cherish_refactor.data.source.remote.api.CheckPhoneRequest
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.databinding.FragmentPhoneBookBinding
import com.example.cherish_refactor.domain.entity.Phone
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.enroll.adapter.PhoneBookAdapter
import com.example.cherish_refactor.util.MyKeyStore
import com.example.cherish_refactor.util.dialog.CheckPhoneDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PhoneBookFragment : BaseFragment<FragmentPhoneBookBinding>(R.layout.fragment_phone_book) {

    private val viewModel: PhoneBookViewModel by viewModels()
    private val phoneBookAdapter: PhoneBookAdapter by lazy { PhoneBookAdapter(true) }
    private val args by navArgs<PhoneBookFragmentArgs>()

    var searchText = ""

    val CAMERA_PERMISSION = arrayOf(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.WRITE_CONTACTS)
    val CAMERA_PERMISSION_REQUEST = 100
    val STORAGE_PERMISSION = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    val STORAGE_PERMISSION_REQUEST = 200

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm=viewModel

        //observer()
        checkPermission()


        return binding.root
    }


    private fun setListeners() {
        phoneBookAdapter.setItemClickListener(object : PhoneBookAdapter.ItemClickListener {
            override fun onchange(radio: Boolean) {
                if (radio) {
                    binding.buttonnext.setBackgroundColor(Color.parseColor("#1AD287"))
                    binding.buttonnext.setTextColor(Color.parseColor("#ffffff"))
                    viewModel.nextPhone()

                }
            }

        })
        binding.buttonnext.setOnClickListener{
            CoroutineScope(IO).launch {
                    val body = CheckPhoneRequest(phoneBookAdapter.phonenumber, MyKeyStore.getUserId()!!)
                    val response = RetrofitBuilder.cherishAPI.checkphone(body)
                withContext(Main){
                    if(response.success){
                        findNavController().navigate(PhoneBookFragmentDirections.actionPhoneBookFragmentToEnrollPlantFragment(phoneBookAdapter.phonename,phoneBookAdapter.phonenumber))

                    }else{
                        CheckPhoneDialogFragment().show(parentFragmentManager,"phonebook")
                    }

                }
            }
            //viewModel.requestCheckPhone(phoneBookAdapter.phonenumber,MyKeyStore.getUserId()!!)
            //observer()
        }
        binding.backPhone.setOnClickListener {
            findNavController().navigate(R.id.action_phoneBookFragment_to_main_home)
        }
    }

   /* fun nextEnroll(){
        if(viewModel.requestCheckPhone(phoneBookAdapter.phonenumber,MyKeyStore.getUserId()!!)==true){
            findNavController().navigate(PhoneBookFragmentDirections.actionPhoneBookFragmentToEnrollPlantFragment(phoneBookAdapter.phonename,phoneBookAdapter.phonenumber))

        }else{
            CheckPhoneDialogFragment().show(parentFragmentManager,"phonebook")

        }
    }
*/
    fun observer(){
        viewModel.isNextPhone.observe(viewLifecycleOwner){
            if(it==false){
                CheckPhoneDialogFragment().show(parentFragmentManager,"phonebook")
            }else{
                findNavController().navigate(PhoneBookFragmentDirections.actionPhoneBookFragmentToEnrollPlantFragment(phoneBookAdapter.phonename,phoneBookAdapter.phonenumber))

            }
        }

    }
    fun checkPermission(){
        val read= ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_CONTACTS)
        if(read == PackageManager.PERMISSION_GRANTED){
            startProcess()
            setListeners()
        }
        else{
            requestPermission()
        }
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_CONTACTS),99)
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            99 -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startProcess()
                    setListeners()
                }
            }
            CAMERA_PERMISSION_REQUEST -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(requireContext(), "Camera Permission Granted", Toast.LENGTH_SHORT).show()
                    // Go Main Function

                }else{
                    Toast.makeText(requireContext(), "Camera Permission Denied", Toast.LENGTH_SHORT).show()
                    // Finish() or Show Guidance on the need for permission
                }
            }
            STORAGE_PERMISSION_REQUEST -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(requireContext(), "Storage Permission Granted", Toast.LENGTH_SHORT).show()
                    // Go Main Function

                }else{
                    Toast.makeText(requireContext(), "Storage Permission Denied", Toast.LENGTH_SHORT).show()
                    // Finish() or Show Guidance on the need for permission
                }
            }
        }
    }




    fun startProcess() {
        setList()
        setSearchListener()

    }


    private fun setSearchListener() {
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
                    searchText = s.toString()
                    if(searchText.length>=0) {
                        changeList()
                    }
                }
            })
    }

    fun changeList() {
        phoneBookAdapter.setItem(getPhoneNumbers(searchText))

    }

    private fun setList() {
        binding.recycler.adapter = phoneBookAdapter
        binding.recycler.layoutManager = LinearLayoutManager(context)
        phoneBookAdapter.setItem(getPhoneNumbers(searchText))
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


}

