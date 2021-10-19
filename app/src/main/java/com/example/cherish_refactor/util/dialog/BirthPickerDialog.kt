package com.example.cherish_refactor.util.dialog

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.DialogBirthPickerBinding

class BirthPickerDialog(private val doAfterConfirm: (date: String) -> Unit) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.dialog_birth_picker, container, false)
        val binding = DialogBirthPickerBinding.bind(view)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val birthMonth:NumberPicker=view.findViewById(R.id.numberPicker)
        val birthDay=view.findViewById<NumberPicker>(R.id.numberPicker3)

        birthDay.removeDivider()
        birthMonth.removeDivider()


        birthMonth.minValue = 1
        birthMonth.maxValue = 12

        birthDay.minValue = 1
        birthDay.maxValue = 31


        //birthMonth.displayedValues = list_every
        // birthDay.displayedValues = list_Day

        birthMonth.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        birthDay.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS


        val btn_ok: Button = view.findViewById(R.id.button_ok)
        btn_ok.setOnClickListener {

            var birthtext =
                birthMonth.value.toString() + "/" + birthDay.value
            doAfterConfirm(birthtext)
            dialog?.dismiss()

        }
        return binding.root
    }

    private fun NumberPicker.removeDivider() {
        val pickerFields = NumberPicker::class.java.declaredFields
        for (pf in pickerFields) {
            if (pf.name == "mSelectionDivider") {
                pf.isAccessible = true
                try {
                    val colorDrawable = ColorDrawable(Color.TRANSPARENT)
                    pf[this] = colorDrawable
                } catch (e: java.lang.IllegalArgumentException) {

                } catch (e: Resources.NotFoundException) {

                } catch (e: IllegalAccessException) {

                }
                break
            }
        }
    }
}
