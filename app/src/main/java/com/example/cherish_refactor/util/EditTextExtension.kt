package com.example.cherish_refactor.util

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent.*
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import com.example.cherish_refactor.R
import com.example.cherish_refactor.util.FlexBoxExtension.addChip
import com.example.cherish_refactor.util.FlexBoxExtension.getChipsCount
import com.example.cherish_refactor.util.dialog.MultiViewDialog
import com.google.android.flexbox.FlexboxLayout

fun EditText.countNumberOfCharacters(observeTextChanged: (CharSequence?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            observeTextChanged(p0)
        }

        override fun afterTextChanged(p0: Editable?) {}
    })
}

fun EditText.writeKeyword(reviewFlexBoxLayout: FlexboxLayout, fragmentManager: FragmentManager) {
    this.setOnKeyListener { view, keyCode, keyEvent ->
        when (keyEvent.action) {
            ACTION_DOWN -> {
                if (keyCode == KEYCODE_ENTER && keyCode != KEYCODE_BACK) {
                    val et = view as EditText
                    val keyword = et.text.toString()
                    if (reviewFlexBoxLayout.getChipsCount() < 3) {
                        reviewFlexBoxLayout.addChip(keyword)
                    } else {
                        MultiViewDialog(
                            R.layout.dialog_warning_keyword_limit_error,
                            0.7f,
                            0.169f
                        ).show(
                            fragmentManager,
                            "editTextExtension"
                        )
                        //this.hideKeyboard()
                    }
                    et.text = null
                }
                return@setOnKeyListener false
            }
            else -> {
                return@setOnKeyListener false
            }
        }
    }
}