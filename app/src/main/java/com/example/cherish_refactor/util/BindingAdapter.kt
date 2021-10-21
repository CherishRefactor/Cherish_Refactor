package com.example.cherish_refactor.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cherish_refactor.R
import com.example.cherish_refactor.data.source.remote.api.CalendarResponse
import com.example.cherish_refactor.ui.detail.calendar.CherishMaterialCalendarView
import com.example.cherish_refactor.ui.detail.calendar.DotDecorator
import com.example.cherish_refactor.util.animation.ProgressbarAnimation


@BindingAdapter("plantUrl")
fun ImageView.setProfileUrl(url: String?) {
    //val ph = placeHolder ?: ContextCompat.getDrawable(context, null)
    Glide.with(context)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

@BindingAdapter("android:setProgressbarAnimation")
fun setProgressbarAnimation(progressbar: ProgressBar, to: Int) {
    val animation = ProgressbarAnimation(progressbar, to.toFloat())
    animation.duration = 2000
    progressbar.startAnimation(animation)
}

@BindingAdapter("android:setProgressPercentText")
fun setProgressPercentText(textView: TextView, percent: Int) {
    textView.text = "${percent}%"
}

@BindingAdapter("android:setProgressBarBackground")
fun setProgressbarBackground(progressbar: ProgressBar, rating: Int) {
    progressbar.progressDrawable = if (rating <= 30) {
        progressbar.context?.let {
            ResourcesCompat.getDrawable(
                it.resources,
                R.drawable.progress_drawable_verticle_red,
                null
            )
        }
    } else {
        progressbar.context?.let {
            ResourcesCompat.getDrawable(
                it.resources,
                R.drawable.progress_drawable_vertical,
                null
            )
        }
    }
}

@BindingAdapter("main_bg")
fun main_bg(
    imageView: ImageView,
    plantName: String?,
) {
    imageView.setBackgroundColor(
        ContextCompat.getColor(
            imageView.context, when (plantName) {
                "민들레" -> R.color.cherish_dandelion_background_color
                "로즈마리" -> R.color.cherish_rosemary_background_color
                "아메리칸블루" -> R.color.cherish_american_blue_background_color
                "스투키" -> R.color.cherish_stuki_background_color
                "단모환" -> R.color.cherish_sun_background_color
                else -> R.color.white
            }
        )
    )

}

@BindingAdapter("main_plant")
fun main_plant(
    imageView: ImageView,
    plantName: String?,
) {
    Glide.with(imageView)
        .asDrawable()
        .load(
            when (plantName) {
                "민들레" -> R.drawable.dandelion_grayshadow_1
                "로즈마리" -> R.drawable.rosemary_grayshadow_1
                "아메리칸블루" -> R.drawable.americanblue_grayshadow_1
                "스투키" -> R.drawable.stuckyi_grayshadow_1
                "단모환" -> R.drawable.cactus_grayshadow_1
                else -> R.drawable.img_white
            }
        )
        .into(imageView)
}

@BindingAdapter("result")
fun setText(view: TextView, text: String?){
    if (text != null) {
        view.text = text.split("\n")[0]
    }
}

@BindingAdapter("result2")
fun setText2(view: TextView, text: String?){
    if (text != null) {
        view.text = text.split("\n")[1]
    }
}

@BindingAdapter("android:delayVisiblity")
fun setDelayVisibility(textView: TextView, dDay: Int) {
    if (dDay < 0)
        textView.visibility = View.INVISIBLE
    else
        textView.visibility = View.VISIBLE
}

@BindingAdapter("android:addDecorator")
fun addDecorator(
    cherishMaterialCalendarView: CherishMaterialCalendarView,
    calendarResponse: CalendarResponse
) {

    calendarResponse.waterData.calendarData.forEach {

        cherishMaterialCalendarView.addDecorator(
            DotDecorator(
                ContextCompat.getColor(
                    cherishMaterialCalendarView.context,
                    R.color.cherish_green_sub
                ), DateUtil.convertDateToCalendarDay(it.wateredDate)
            )
        )
    }
    cherishMaterialCalendarView.addDecorator(
        DotDecorator(
            ContextCompat.getColor(
                cherishMaterialCalendarView.context,
                R.color.cherish_pink_sub
            ), DateUtil.convertDateToCalendarDay(calendarResponse.waterData.futureWaterDate)
        )
    )
}
