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
import com.example.cherish_refactor.databinding.DialogClockPickerBinding

class ClockPickerDialog(private val doAfterConfirm: (date: String) -> Unit) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.dialog_clock_picker,container,false)
        val binding = DialogClockPickerBinding.bind(view)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val clock_hour: NumberPicker = view.findViewById(R.id.numberPicker_clock)
        val clock_minute: NumberPicker = view.findViewById(R.id.numberPicker2_clock)
        val clock_ampm: NumberPicker = view.findViewById(R.id.numberPicker3_clock)

        val list = resources.getStringArray(R.array.ampm)
        val listm = "00"

        clock_hour.removeDivider()
        clock_minute.removeDivider()
        clock_ampm.removeDivider()

        clock_hour.minValue = 1
        clock_hour.maxValue = 12

        /* clock_minute.minValue = 00
         clock_minute.maxValue = 00*/
        clock_minute.displayedValues = arrayOf(listm)

        clock_ampm.minValue = 0
        clock_ampm.maxValue = list.size - 1

        clock_ampm.displayedValues = list

        clock_hour.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        clock_ampm.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        clock_minute.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS


        val btn_ok: Button = view.findViewById(R.id.button_ok_clock)
        btn_ok.setOnClickListener {
            if ((clock_minute.value.toString()).length < 2) {
                if (list[clock_ampm.value] == "AM") {
                    var clocktext = clock_hour.value.toString() + ":0" + clock_minute.value.toString() + " " + list[clock_ampm.value]
                    doAfterConfirm(clocktext)
                } else {
                    val clock = clock_hour.value + 12
                    var clocktext = clock.toString() + ":0" + clock_minute.value.toString() + " " + list[clock_ampm.value]
                    doAfterConfirm(clocktext)
                }
            } else {
                if (list[clock_ampm.value] == "PM") {
                    val clock = clock_hour.value + 12
                    var clocktext = clock.toString() + ":" + clock_minute.value.toString() + " " + list[clock_ampm.value]
                    doAfterConfirm(clocktext)

                } else {
                    var clocktext = clock_hour.value.toString() + ":" + clock_minute.value.toString() + " " + list[clock_ampm.value]
                    doAfterConfirm(clocktext)
                }
            }
            dismiss()
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
