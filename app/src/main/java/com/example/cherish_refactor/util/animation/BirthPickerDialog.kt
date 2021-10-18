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
import com.example.cherish_refactor.databinding.DialogBirthPickerBinding

class BirthPickerDialog(private val doAfterConfirm: (date: String) -> Unit) :
    DialogFragment() {

    private lateinit var _binding: DialogBirthPickerBinding
    val binding get() = _binding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogBirthPickerBinding.inflate(requireActivity().layoutInflater)

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

        val birthMonth=binding.numberPicker
        val birthDay=binding.numberPicker3

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

        var birthtext = birthMonth.value.toString() + "/" + birthDay.value
        doAfterConfirm(birthtext)

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
        binding.buttonAlarm.setOnClickListener {
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
