package com.example.cherish_refactor.util.animation

import android.app.AlertDialog
import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.DialogDatePickerBinding

class DatePickerDialog(private val doAfterConfirm: (date: String) -> Unit) :
    DialogFragment() {

    private lateinit var _binding: DialogDatePickerBinding
    val binding get() = _binding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogDatePickerBinding.inflate(requireActivity().layoutInflater)

        setConfirmTextClickListener()
        setCancelTextClickListener()
        return activity?.let {
            val dialog = AlertDialog.Builder(it).create()
            dialog.setView(binding?.root)
            dialog
        } ?: throw IllegalStateException()
    }

    override fun onStart() {
        super.onStart()
        setLayout()
    }

    private fun setLayout() {
        requireNotNull(dialog).apply {
            requireNotNull(window).apply {
                setLayout(
                    (resources.displayMetrics.widthPixels * 0.8).toInt(),
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                //setBackgroundDrawableResource(R.drawable.border_gray_f3f5f8_fill_10)
            }
        }
    }

    private fun sendSelectedDate() {
        /*with(binding.datePickerDialogDatePicker) {
            val yearFormat = getYearFormat(year)
            val monthFormat = getMonthFormat(month)
            val dayFormat = getDayFormat(dayOfMonth)
            val date = "$yearFormat.$monthFormat.$dayFormat"
            val networkDate = "$yearFormat-$monthFormat-$dayFormat"
            doAfterConfirm(date, networkDate)
        }*/

        val week_every=binding.numberPicker
        val week_number=binding.numberPicker2
        val week_month=binding.numberPicker3

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

        val weektext =list_every[week_every.value] + " " + week_number.value.toString() + " " + list_Day[week_month.value]
        doAfterConfirm(weektext)

        dismiss()
    }

    private fun getYearFormat(year: Int): String = year.toString()

    private fun getMonthFormat(month: Int): String {
        return if (month < 9) {
            "0" + (month + 1).toString()
        } else {
            (month + 1).toString()
        }
    }

    private fun getDayFormat(day: Int): String {
        return if (day < 10) {
            "0$day"
        } else {
            day.toString()
        }
    }

    private fun setConfirmTextClickListener() {
        binding.buttonOk.setOnClickListener {
            sendSelectedDate()
        }
    }

    private fun setCancelTextClickListener() {
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
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
