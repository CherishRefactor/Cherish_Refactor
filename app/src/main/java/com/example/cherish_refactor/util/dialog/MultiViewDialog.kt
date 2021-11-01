package com.example.cherish_refactor.util.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.DialogLoadingBinding
import com.example.cherish_refactor.databinding.DialogWarningKeywordLimitErrorBinding
import com.example.cherish_refactor.databinding.DialogWarningKeywordWordcountLimitErrorBinding
import com.example.cherish_refactor.databinding.DialogWarningReviewLimitErrorBinding
import com.example.cherish_refactor.util.DialogUtil


class MultiViewDialog(
    @LayoutRes private val layoutResId: Int,
    private val widthRatio: Float,
    private val heightRatio: Float
) : DialogFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutResId, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return when (layoutResId) {
            R.layout.dialog_warning_review_limit_error -> {
                val binding = DialogWarningReviewLimitErrorBinding.bind(view)
                binding.root
            }
            R.layout.dialog_warning_keyword_limit_error -> {
                val binding = DialogWarningKeywordLimitErrorBinding.bind(view)
                binding.root
            }
            R.layout.dialog_warning_keyword_wordcount_limit_error -> {
                val binding = DialogWarningKeywordWordcountLimitErrorBinding.bind(view)
                binding.root
            }
            R.layout.dialog_loading -> {
                val binding = DialogLoadingBinding.bind(view)
                Glide.with(requireContext())
                    .asGif()
                    .load(R.drawable.cherish_loading)
                    .into(binding.dialogLoadingImage)
                binding.root
            }
            // 리뷰를 적지 않았을 때 떠야하는 dialog
            R.layout.dialog_warning_review_no_word_warning -> {
                val binding = DialogWarningReviewLimitErrorBinding.bind(view)
                binding.root
            }
            else -> {
                view
            }
        }
    }

    override fun onClick(p0: View?) {
        dismiss()
    }

   override fun onResume() {
        super.onResume()
        DialogUtil.adjustDialogSize(this, widthRatio = widthRatio, heightRatio = heightRatio)
    }
}