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
import com.example.cherish_refactor.databinding.FragmentPhoneBookBinding
import com.example.cherish_refactor.domain.entity.Phone
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.enroll.adapter.PhoneBookAdapter
import com.example.cherish_refactor.util.MyKeyStore
import com.example.cherish_refactor.util.dialog.CheckPhoneDialogFragment


class PhoneBookFragment : BaseFragment<FragmentPhoneBookBinding>(R.layout.fragment_phone_book) {

    private val viewModel: PhoneBookViewModel by viewModels()
    private val phoneBookAdapter: PhoneBookAdapter by lazy { PhoneBookAdapter() }
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
        checkPermission(CAMERA_PERMISSION, CAMERA_PERMISSION_REQUEST)
        checkPermission(STORAGE_PERMISSION, STORAGE_PERMISSION_REQUEST)

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
            viewModel.requestCheckPhone(phoneBookAdapter.phonenumber,MyKeyStore.getUserId()!!)


            viewModel.isCheckPhone.observe(viewLifecycleOwner){
                if(it==false){
                    CheckPhoneDialogFragment().show(parentFragmentManager,"phonebook")
                }else{
                    findNavController().navigate(PhoneBookFragmentDirections.actionPhoneBookFragmentToEnrollPlantFragment(phoneBookAdapter.phonename,phoneBookAdapter.phonenumber))

                }
            }
            // 이름 , 전화번호
        }
    }

    /*fun observer(){
        viewModel.isCheckPhone.observe(viewLifecycleOwner){
            if(it==false){
                CheckPhoneDialogFragment().show(parentFragmentManager,"phonebook")
            }else{
                findNavController().navigate(PhoneBookFragmentDirections.actionPhoneBookFragmentToEnrollPlantFragment(phoneBookAdapter.phonename,phoneBookAdapter.phonenumber))

            }
        }

    }*/

    fun checkPermission(permissions: Array<String>, permissionRequestNumber:Int){
        val permissionResult = ContextCompat.checkSelfPermission(requireContext(), permissions[0])

        when(permissionResult){
            PackageManager.PERMISSION_GRANTED -> {
                //Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
                // Go Main Function
                startProcess()
                setListeners()
            }
            PackageManager.PERMISSION_DENIED -> {
                ActivityCompat.requestPermissions(requireActivity(), permissions, permissionRequestNumber)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
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
                    changeList()
                }
            })
    }

    fun changeList() {
        phoneBookAdapter.setItem(getPhoneNumbers(searchText))
        phoneBookAdapter.notifyDataSetChanged()

    }


    private fun setList() {
        binding.recycler.adapter = phoneBookAdapter
        binding.recycler.layoutManager = LinearLayoutManager(context)
        phoneBookAdapter.setItem(getPhoneNumbers(searchText))
    }


    private fun getPhoneNumbers(search: String): List<Phone> {

        val list = mutableListOf<Phone>()
        val phonUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        // 2.1 전화번호에서 가져올 컬럼 정의
        val projections = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        // 2.2 조건 정의
        var where: String? = null
        var whereValues: Array<String>? = null
        // searchName에 값이 있을 때만 검색을 사용한다
        if (search.isNotEmpty()) {

            where = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE ? OR "+ContactsContract.CommonDataKinds.Phone.NUMBER + " LIKE ?"
            whereValues = arrayOf("%$search%")
        }

        context?.run {
            val cursor = contentResolver.query(phonUri, projections, where, whereValues,null)
            while (cursor?.moveToNext() == true) {
                val id = cursor.getString(0)
                val name = cursor.getString(1)
                val number = cursor.getString(2)
                val phone = Phone(id, name, number)
                list.add(phone)
            }
        }
        return list
    }


}

