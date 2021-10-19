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
import com.example.cherish_refactor.databinding.DialogDatePickerBinding

class DatePickerDialog(private val doAfterConfirm: (date: String) -> Unit) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.dialog_date_picker, container, false)
        val binding=DialogDatePickerBinding.bind(view)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val week_every: NumberPicker = view.findViewById(R.id.numberPicker)
        val week_number: NumberPicker = view.findViewById(R.id.numberPicker2)
        val week_month: NumberPicker = view.findViewById(R.id.numberPicker3)

        val list_cycle = resources.getStringArray(R.array.cycle)
        val list_every = resources.getStringArray(R.array.week)
        val list_Day = resources.getStringArray(R.array.day)

        week_every.removeDivider()
        week_number.removeDivider()
        week_month.removeDivider()

        week_every.minValue = 0
        week_every.maxValue = list_every.size - 1

        week_number.minValue = 1
        week_number.maxValue = 90

        week_month.minValue = 0
        week_month.maxValue = list_Day.size - 1

        week_every.displayedValues = list_every
        week_month.displayedValues = list_Day

        week_every.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        week_month.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        week_number.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS


        val btn_ok: Button = view.findViewById(R.id.button_ok)
        btn_ok.setOnClickListener {
            val weektext =list_every[week_every.value] + " " + week_number.value.toString() + " " + list_Day[week_month.value]
            doAfterConfirm(weektext)
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
