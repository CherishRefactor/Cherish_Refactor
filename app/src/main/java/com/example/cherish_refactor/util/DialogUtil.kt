package com.example.cherish_refactor.util

import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.cherish_refactor.MainApplication

object DialogUtil {
    fun adjustDialogSize(dialogFragment: DialogFragment, widthRatio: Float, heightRatio: Float) {
        val dialogParams: WindowManager.LayoutParams = dialogFragment.dialog!!.window!!.attributes

        (MainApplication.pixelRatio.screenWidth * widthRatio).toInt()
            .also { dialogParams.width = it }
        (MainApplication.pixelRatio.screenHeight * heightRatio).toInt()
            .also { dialogParams.height = it }

        dialogFragment.dialog!!.window!!.apply {
            attributes = dialogParams
        }
    }
}