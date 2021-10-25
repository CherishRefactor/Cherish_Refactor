package com.example.cherish_refactor.ui.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentSignUpFirstBinding
import com.example.cherish_refactor.ui.base.BaseFragment

class SignUpFirstFragment : BaseFragment<FragmentSignUpFirstBinding>(R.layout.fragment_sign_up_first) {

    var email: String = ""
    var pw: String = ""
    var pwAgain: String = "1"
    var checkEmail: Boolean = false //이메일 형식확인
    var isValid: Boolean = false //이메일 중복확인
    var isFinish: Boolean = false
    var isValidPW: Boolean = false


    private val signUpViewModel : SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = signUpViewModel
        checkEmail()
        observers()
        return binding.root
    }
    
    fun observers(){


        signUpViewModel.isEmailCheck.observe(viewLifecycleOwner){

            if(it == true){
                binding.isUsableEmail.text = "사용가능한 이메일입니다."
                binding.isUsableEmail.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.cherish_green_main
                    )
                )
                binding.signUpButton.setOnClickListener {

                        showPw()

                }
            }else{
                binding.isUsableEmail.text = "이미 존재하는 이메일입니다."
                binding.isUsableEmail.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.cherish_pink_sub
                    )
                )

            }
        }

    }





    private fun checkEmail() {
        isFinish = false

        binding.userEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                email = signUpViewModel.email.value!!

                binding.isUsableEmail.setTextAppearance(R.style.SignUpTextAppearance)

                checkEmail = isEmailValid(email) //이메일 형식 확인

                if (checkEmail) { //이메일 형식 올바르면
                    signUpViewModel.requestSignUpEmail()
                    //checkSameEmail(email) //이메일 중복 확인

                    binding.isUsableEmail.text = "사용가능한 이메일입니다."
                    binding.isUsableEmail.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_green_main
                        )
                    )

                    //버튼 초록색 활성화
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

                    if (!isFinish) {

                    }


                } else { //이메일 형식 올바르지 않으면
                    binding.signUpButton.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_light_gray
                        )
                    )
                    binding.signUpButton.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_pass_text_gray
                        )
                    )
                    binding.isUsableEmail.text = "사용하실 수 없는 이메일입니다."
                    binding.isUsableEmail.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_pink_sub
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

    private fun isEmailValid(email: String): Boolean {
        isFinish = false
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }
        return true
    }

    private fun checkSameEmail(userEmail: String) {
        isFinish = false

        if (!isFinish) {
            signUpViewModel.requestSignUpEmail()
            isValid = signUpViewModel.isEmailCheck.value!!

        }

    }


    private fun showPw() {
        binding.pwText.visibility = View.VISIBLE
        binding.userPwLayout.visibility = View.VISIBLE
        binding.pwAgainLayout.visibility = View.VISIBLE

        binding.signUpButton.text = "다음"

        binding.userPw.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                isFinish = false
                pw = binding.userPw.text.toString()
                binding.isUsablePw.visibility = View.VISIBLE

                isValidPW = isValidPW(pw) //비밀번호 형식 확인
                Log.d("비밀번호 형식 확인", isValidPW.toString())
                Log.d("pw", pw)
                if (isValidPW) {
                    binding.isUsablePw.text = "사용가능한 비밀번호입니다."
                    binding.isUsablePw.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_green_main
                        )
                    )

                    checkPwAgain() //비밀번호 다시 입력받음
                } else {
                    binding.isUsablePw.text = "사용하실 수 없는 비밀번호입니다."
                    binding.isUsablePw.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.cherish_pink_sub
                        )
                    )
                }
                checkPW() //두 개 비밀번호 일치하는지 확인
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun isValidPW(password: String): Boolean {
        Log.d("isValidPW", password)
        val reg =
            Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*^#?&]).{8,}.\$")
        if (!password.matches(reg)) {
            return false
        }
        return true
    }

    private fun checkPwAgain() {
        binding.userPwAgain.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                pwAgain = binding.userPwAgain.text.toString()
                Log.d("pwagain", pwAgain)
                checkPW()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        if (pw == pwAgain) {
            binding.isUsablePw.text = "비밀번호가 일치합니다."
            binding.isUsablePw.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.cherish_green_main
                )
            )
            isFinish = true
            binding.signUpButton.setOnClickListener {
                if (pw == pwAgain) {
                    val bundle = Bundle()
                    bundle.putString("email", email)
                    bundle.putString("password", pw)

                    (activity as SignUpActivity).postData(bundle)
                    (activity as SignUpActivity).replaceFragment(1)
                }
            }
        }
        if (pwAgain != "1") {
            binding.isUsablePw.text = "비밀번호가 일치하지 않습니다."
            binding.isUsablePw.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.cherish_pink_sub
                )
            )
            isFinish = false
        }
    }

    private fun checkPW() {
        if (pw == pwAgain) {
            binding.isUsablePw.text = "비밀번호가 일치합니다."
            binding.isUsablePw.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.cherish_green_main
                )
            )

            binding.signUpButton.setOnClickListener {
                if (pw == pwAgain) {
                    val bundle = Bundle()
                    bundle.putString("email", email)
                    bundle.putString("password", pw)

                    (activity as SignUpActivity).postData(bundle)
                    (activity as SignUpActivity).replaceFragment(1)
                }
            }
        }
    }

}