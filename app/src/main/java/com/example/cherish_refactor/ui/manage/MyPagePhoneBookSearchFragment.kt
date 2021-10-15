package com.example.cherish_refactor.ui.manage

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cherish_refactor.R
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.databinding.FragmentMyPagePhoneBookSearchBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.manage.adapter.MypagePhoneBookSearchAdapter
import com.example.cherish_refactor.ui.manage.adapter.Phonemypage

class MyPagePhoneBookSearchFragment : BaseFragment<FragmentMyPagePhoneBookSearchBinding>(R.layout.fragment_my_page_phone_book_search) {
    private val viewModel: PlantViewModel by viewModels()
    private val requestData = RetrofitBuilder
    lateinit var madapter: MypagePhoneBookSearchAdapter
    var phonelist = mutableListOf<Phonemypage>()
    var searchText = ""
    var sortText = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startProcess()

    }

    fun startProcess() {
        setList()
        setSearchListener()
    }

    fun setSearchListener() {
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
        madapter.setItemClickListener(object : MypagePhoneBookSearchAdapter.ItemClickListener {
            @SuppressLint("ResourceAsColor")
            override fun onchange(radio: Boolean) {
                if (radio) {
                    binding.myPageAddPhoneBtn.setBackgroundColor(Color.parseColor("#1AD287"))

                }
            }

        })
        binding.phonebookcancel.setOnClickListener {
            binding.editSearch.setText("")
        }
        /*binding.myPageAddPhoneBtn.setOnClickListener {
            val phonenumber = madapter.phonenumber
            val user_id = viewModel.cherishUserId.value
            val body = RequestCheckPhoneData(phone = phonenumber.toString(), UserId = user_id!!)
            requestData.checkphoneAPI.checkphone(body)
                .enqueue(
                    object : Callback<ResponseCheckPhoneData> {
                        override fun onFailure(
                            call: Call<ResponseCheckPhoneData>,
                            t: Throwable
                        ) {
                            Log.d("통신 실패", t.toString())
                        }

                        override fun onResponse(
                            call: Call<ResponseCheckPhoneData>,
                            response: Response<ResponseCheckPhoneData>
                        ) {
                            if (response.body() == null) {
                               *//* CheckPhoneDialogFragment(
                                    R.layout.fragment_check_phone_dialog,

                                    ).show(
                                    parentFragmentManager, "asdf"
                                )*//*

                            }
                            response.takeIf {
                                it.isSuccessful
                            }?.body()
                                ?.let { it ->
                                  *//*  var intent =
                                        Intent(context, EnrollmentPhoneActivity::class.java)
                                    intent.putExtra("name", madapter.phonename)
                                    intent.putExtra("phone", madapter.phonenumber)
                                    intent.putExtra("check", 0)
                                    intent.putExtra("userId", viewModel.cherishUserId.value)
                                    startActivity(intent)*//*
                                }
                        }
                    }
                )

        }*/
    }

    fun changeList() {
        val newList = getPhoneNumbers(sortText, searchText)
        this.phonelist.clear()
        this.phonelist.addAll(newList)
        this.madapter.notifyDataSetChanged()
    }

    fun setList() {
        phonelist.addAll(getPhoneNumbers(sortText, searchText))

        madapter = MypagePhoneBookSearchAdapter(phonelist)
        binding.recyclerMypage.adapter = madapter
        binding.recyclerMypage.layoutManager = LinearLayoutManager(context)
    }


    fun getPhoneNumbers(sort: String, name: String): List<Phonemypage> {
        val list = mutableListOf<Phonemypage>()

        val phonUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        // 2.1 전화번호에서 가져올 컬럼 정의
        val projections = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        // 2.2 조건 정의
        var where: String? = null
        var where2: String? = null

        var whereValues: Array<String>? = null
        // searchName에 값이 있을 때만 검색을 사용한다
        if (name.isNotEmpty()) {
            where = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " like ?"
            where2 = ContactsContract.CommonDataKinds.Phone.NUMBER + " like ?"

            whereValues = arrayOf("%$name%")
        }

        val optionSort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " $sort"

        context?.run {


            val cursor = contentResolver.query(phonUri, projections, where, whereValues, optionSort)
            while (cursor?.moveToNext() == true) {
                val id = cursor.getString(0)
                val name = cursor.getString(1)
                val number = cursor.getString(2)

                val Phonemypage = Phonemypage(id, name, number)

                list.add(Phonemypage)
                //list.distinct()

            }

            val cursor2 =
                contentResolver.query(phonUri, projections, where2, whereValues, optionSort)
            while (cursor2?.moveToNext() == true) {
                val id = cursor2.getString(0)
                val name = cursor2.getString(1)
                val number = cursor2.getString(2)

                val Phonemypage = Phonemypage(id, name, number)

                list.add(Phonemypage)
                // list.distinct()
            }

        }

        // 결과목록 반환
        return list.distinct()
    }


}