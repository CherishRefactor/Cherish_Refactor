package com.example.cherish_refactor.ui.detail.calendar

import android.content.Context
import android.util.AttributeSet
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView


class CherishMaterialCalendarView constructor(context: Context, attrs: AttributeSet) :
    MaterialCalendarView(context, attrs) {

    fun changeCalendarModeWeeks() {
        state().edit().setCalendarDisplayMode(CalendarMode.WEEKS).commit()
    }

    fun changeCalendarModeMonths() {
        state().edit().setCalendarDisplayMode(CalendarMode.MONTHS).commit()
    }

}