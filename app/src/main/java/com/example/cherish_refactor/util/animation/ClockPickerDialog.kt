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
import com.example.cherish_refactor.databinding.DialogClockPickerBinding

class ClockPickerDialog(private val doAfterConfirm: (date: String) -> Unit) :
    DialogFragment() {

    private lateinit var _binding: DialogClockPickerBinding
    val binding get() = _binding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogClockPickerBinding.inflate(requireActivity().layoutInflater)

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

        val clock_hour=binding.numberPickerClock
        val clock_minute=binding.numberPicker2Clock
        val clock_ampm=binding.numberPicker3Clock

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
        binding.buttonOkClock.setOnClickListener {
            sendSelectedDate()
        }
    }

    private fun setCancelTextClickListener() {
        binding.buttonCancelClock.setOnClickListener {
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
