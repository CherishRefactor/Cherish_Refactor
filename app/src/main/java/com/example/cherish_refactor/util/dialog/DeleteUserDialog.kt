package com.example.cherish_refactor.util.dialog


import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.cherish_refactor.MainApplication
import com.example.cherish_refactor.R
import com.example.cherish_refactor.data.source.remote.singleton.RetrofitBuilder
import com.example.cherish_refactor.databinding.DialogDeleteUserBinding
import com.example.cherish_refactor.ui.splash.SplashActivity
import com.example.cherish_refactor.util.MyKeyStore.deleteToken
import com.example.cherish_refactor.util.MyKeyStore.deleteUserId
import com.example.cherish_refactor.util.MyKeyStore.deleteUserPassword

class DeleteUserDialog(private val doAfterConfirm: () -> Unit) : DialogFragment() {

    private lateinit var binding: DialogDeleteUserBinding
    private val requestData = RetrofitBuilder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.dialog_delete_user, container, false)
        binding = DialogDeleteUserBinding.bind(view)


        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setListeners()
        return binding.root


    }


    fun setListeners(){
        binding.buttonClose.setOnClickListener {
            dismiss()
        }

        binding.buttonCopy.setOnClickListener {
            //shortToast(requireContext(), "계정이 삭제되었습니다.")
            //quitCherish()
            doAfterConfirm()
            MainApplication.apply {
                deleteToken()
                deleteUserId()
                deleteUserPassword()
            }
            requireActivity().finish()
            val intent = Intent(requireActivity(), SplashActivity::class.java)
            startActivity(intent)
            dismiss()
        }
    }

}