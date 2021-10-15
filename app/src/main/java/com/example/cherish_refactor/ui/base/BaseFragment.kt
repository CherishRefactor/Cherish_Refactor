package com.example.cherish_refactor.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    Fragment() {

    protected lateinit var binding: B
        private set

    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        initBinding(inflater, container)
        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate<B>(inflater, layoutResId, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    fun showLoading() {
        if (progressDialog != null) return

        progressDialog = ProgressDialog(requireContext())
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            progressDialog?.show()
        }
    }

    fun hideLoading() {
        progressDialog?.dismiss()
        progressDialog = null
    }


    fun showShortToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}