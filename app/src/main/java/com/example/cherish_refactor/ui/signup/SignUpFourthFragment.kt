package com.example.cherish_refactor.ui.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextPaint
import android.text.TextWatcher
import android.text.style.URLSpan
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cherish_refactor.R
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.databinding.FragmentSignUpFourthBinding
import com.example.cherish_refactor.ui.signin.SignInActivity
import java.util.regex.Matcher
import java.util.regex.Pattern


class SignUpFourthFragment : Fragment() {
    lateinit var binding: FragmentSignUpFourthBinding
    var nickName: String = ""
    var email: String = ""
    var password: String = ""
    var phone: String = ""
    var sex: Boolean = true
    var birth: String = ""
    private val requestData = RetrofitBuilder

    private val signUpViewModel:SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up_fourth, container, false)

        binding = FragmentSignUpFourthBinding.bind(view)

        val bundle = (activity as SignUpActivity).mBundle

        email = bundle.getString("email").toString()
        password = bundle.getString("password").toString()
        phone = bundle.getString("phone").toString()
        sex = true
        birth = "0000"

        initializeLink()
        initializeNickName()

        return view
    }

    private fun initializeLink() {
        val transform =
            Linkify.TransformFilter(object : Linkify.TransformFilter, (Matcher, String) -> String {
                override fun transformUrl(match: Matcher?, url: String?): String {
                    return ""
                }

                override fun invoke(p1: Matcher, p2: String): String {
                    return ""
                }
            })

        val personalSecurity = Pattern.compile("?????????????????? ??????")
        val service = Pattern.compile("????????? ?????? ??????")
        Linkify.addLinks(
            binding.serviceText,
            personalSecurity,
            "https://www.notion.so/Cherish-2d35c1bffa2f4d49943db302d76e3cac",
            null,
            transform
        )
        Linkify.addLinks(
            binding.serviceText,
            service,
            "https://www.notion.so/Cherish-d96f88172ffa4d80b257665849bddc65",
            null,
            transform
        )

        binding.serviceText.removeUnderline()
    }

    private fun TextView.removeUnderline() {
        val spannable = SpannableString(text)
        for (u in spannable.getSpans(0, spannable.length, URLSpan::class.java)) {
            spannable.setSpan(object : URLSpan(u.url) {
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }, spannable.getSpanStart(u), spannable.getSpanEnd(u), 0)
        }
        text = spannable
    }

    private fun initializeNickName() {
        binding.userNickname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                nickName = binding.userNickname.text.toString()


                if (nickName.length <= 8) {
                    binding.isUsableNickname.visibility = View.VISIBLE
                    binding.isUsableNickname.text = "???????????? ??? ?????? ??????????????????."
                    binding.isUsableNickname.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_green_main
                        )
                    )

                    //?????? ????????? ?????????
                    binding.signUpButton.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_green_main
                        )
                    )
                    binding.signUpButton.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )

                    binding.signUpButton.setOnClickListener {






                        requestServer()
                    }
                } else {
                    binding.isUsableNickname.text = "???????????? ??? ?????? ??????????????????."
                    binding.isUsableNickname.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_pink_sub
                        )
                    )

                    //?????? ????????????
                    binding.signUpButton.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_text_box_gray
                        )
                    )
                    binding.signUpButton.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_text_gray
                        )
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private  fun requestServer() {
        Log.d("final email", email)
        Log.d("final password", password)
        Log.d("nickname", nickName)
        Log.d("phpone", phone)
        Log.d("sex", sex.toString())
        Log.d("birth", birth)

        signUpViewModel.requestSignUp(email,password,nickName,phone,sex.toString(),birth)

        val intent = Intent(context, SignInActivity::class.java)
        startActivity(intent)
        requireActivity().finish()

    }

}