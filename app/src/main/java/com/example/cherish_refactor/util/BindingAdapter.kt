package com.example.cherish_refactor.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.cherish_refactor.R
import com.example.cherish_refactor.data.source.remote.api.CalendarResponse
import com.example.cherish_refactor.ui.detail.calendar.CherishMaterialCalendarView
import com.example.cherish_refactor.ui.detail.calendar.DotDecorator
import com.example.cherish_refactor.util.FlexBoxExtension.addChipCalendar
import com.example.cherish_refactor.util.FlexBoxExtension.clearChips
import com.example.cherish_refactor.util.ImageViewExtension.matchSizeImageView
import com.example.cherish_refactor.util.ImageViewExtension.resizeImageView
import com.example.cherish_refactor.util.ImageViewExtension.setMargin
import com.example.cherish_refactor.util.PixelUtil.dp
import com.example.cherish_refactor.util.animation.ProgressbarAnimation
import com.google.android.flexbox.FlexboxLayout
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlin.math.abs


@BindingAdapter("plantUrl")
fun ImageView.setProfileUrl(url: String?) {
    //val ph = placeHolder ?: ContextCompat.getDrawable(context, null)
    Glide.with(context)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}
@BindingAdapter("android:setSelectedPlantStrokeColor")
fun setSelectedPlantStrokeColor(imageView: ImageView, plantName: String?) {
    imageView.setImageDrawable(
        ContextCompat.getDrawable(
            imageView.context, when (plantName) {
                "민들레" -> R.drawable.home_selected_plant_indicator_dandelion
                "로즈마리" -> R.drawable.home_selected_plant_indicator_rosemary
                "아메리칸블루" -> R.drawable.home_selected_plant_indicator_american_blue
                "스투키" -> R.drawable.home_selected_plant_indicator_stuki
                "단모환" -> R.drawable.home_selected_plant_indicator_cactus
                else -> R.drawable.home_selected_plant_indicator_rosemary
            }
        )
    )
}

@BindingAdapter("homeday")
fun homeDay(textView: TextView,dDay: Int){
    if(dDay==0){
        textView.text = "D-Day"
    }else if(dDay<0){
        textView.text="D+${abs(dDay)}"
    }else{
        textView.text="D-${dDay}"
    }

}

@BindingAdapter("setResult")
fun setResult(view: View,plantId :Int){
    when (plantId) {
        1 -> {
            view.setBackgroundColor(Color.parseColor("#F1B0BC"))
        }
        2 -> {
            view.setBackgroundColor(Color.parseColor("#AAB3D1"))

        }
        3 -> {
            view.setBackgroundColor(Color.parseColor("#97CDBD"))

        }
        4 -> {
            view.setBackgroundColor(Color.parseColor("#9AB7DE"))
        }
        5 -> {
            view.setBackgroundColor(Color.parseColor("#9EC8EC"))
        }
    }

}
@BindingAdapter("setResultCons")
fun setResultCons(constraintLayout: ConstraintLayout,plantId :Int){
    when (plantId) {
        1 -> {
            constraintLayout.setBackgroundResource(R.drawable.plant_tip_box)
        }
        2 -> {
            constraintLayout.setBackgroundResource(R.drawable.plant_tip_box2)

        }
        3 -> {
            constraintLayout.setBackgroundResource(R.drawable.plant_tip_box3)

        }
        4 -> {
            constraintLayout.setBackgroundResource(R.drawable.plant_tip_box4)
        }
        5 -> {
            constraintLayout.setBackgroundResource(R.drawable.plant_tip_box5)
        }
    }

}

@BindingAdapter("setResultText")
fun setResultText(textView: TextView,plantId: Int){
    when (plantId) {
        1 -> {
            textView.setTextColor(Color.parseColor("#F1B0BC"))
            textView.setTextColor(Color.parseColor("#F1B0BC"))
        }
        2 -> {
            textView.setTextColor(Color.parseColor("#AAB3D1"))
            textView.setTextColor(Color.parseColor("#AAB3D1"))

        }
        3 -> {
            textView.setTextColor(Color.parseColor("#97CDBD"))
            textView.setTextColor(Color.parseColor("#97CDBD"))

        }
        4 -> {
            textView.setTextColor(Color.parseColor("#9AB7DE"))
            textView.setTextColor(Color.parseColor("#9AB7DE"))
        }
        5 -> {
            textView.setTextColor(Color.parseColor("#9EC8EC"))
            textView.setTextColor(Color.parseColor("#9EC8EC"))
        }
    }


}
@BindingAdapter("setResultBtn")
fun setResultBtn(button: Button,plantId: Int){
    when (plantId) {
        1 -> {
            button.setBackgroundColor(Color.parseColor("#F1B0BC"))

        }
        2 -> {
            button.setBackgroundColor(Color.parseColor("#AAB3D1"))


        }
        3 -> {
            button.setBackgroundColor(Color.parseColor("#97CDBD"))


        }
        4 -> {
            button.setBackgroundColor(Color.parseColor("#9AB7DE"))

        }
        5 -> {
            button.setBackgroundColor(Color.parseColor("#9EC8EC"))

        }
    }


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

@BindingAdapter("setBirth")
fun setBirth(view: TextView, birth: String?) {
    if (birth == "Invalid Date") {
        view.text = "_ _"
    } else {
        view.text = birth
    }
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

@BindingAdapter(value = ["setImageSizePlantName", "setImageSizeGrowth", "setImageSizedDay"])
fun setPlantImageViewSize(
    imageView: ImageView,
    plantName: String?,
    growth: Int?,
    dDay: Int?,
) {
    if (growth != null) {
        imageView.apply {
            when {
                growth <= 25 -> { // 1단계인 경우
                    when (plantName) {
                        "민들레" -> {
                            resizeImageView(262, 331)
                            setMargin(
                                top = 0.dp,
                                start = 0.dp,
                                end = 0.dp,
                                bottom = 40.dp
                            )
                        }
                        "로즈마리" -> {
                            resizeImageView(220, 380)
                            setMargin(
                                top = 0.dp,
                                start = 0.dp,
                                end = 0.dp,
                                bottom = 44.dp
                            )
                        }
                        "아메리칸블루" -> {
                            resizeImageView(249, 368)
                            setMargin(
                                top = 0.dp,
                                start = 0.dp,
                                end = 0.dp,
                                bottom = 40.dp
                            )
                        }
                        "스투키" -> {
                            resizeImageView(295, 266.6.toInt())
                            setMargin(
                                top = 0.dp,
                                start = 0.dp,
                                end = 0.dp,
                                bottom = 40.dp
                            )
                        }
                        "단모환" -> {
                            resizeImageView(275, 229)
                            setMargin(
                                top = 0.dp,
                                start = 0.dp,
                                end = 0.dp,
                                bottom = 44.dp
                            )
                        }
                        else -> {

                        }
                    }
                }
                growth in 26..50 -> { // 2단계인 경우
                    when (plantName) {
                        "민들레" -> {
                            resizeImageView(235, 388)
                            setMargin(
                                top = 0.dp,
                                start = 0.dp,
                                end = 0.dp,
                                bottom = 40.dp
                            )
                        }
                        "로즈마리" -> {
                            resizeImageView(192, 500)
                            setMargin(
                                top = 0.dp,
                                start = 0.dp,
                                end = 0.dp,
                                bottom = 40.dp
                            )
                        }
                        "아메리칸블루" -> {
                            resizeImageView(204, 461)
                            setMargin(
                                top = 0.dp,
                                start = 0.dp,
                                end = 22.dp,
                                bottom = 40.dp
                            )
                        }
                        "스투키" -> {
                            resizeImageView(240, 313)
                            setMargin(
                                top = 0.dp,
                                start = 0.dp,
                                end = 0.dp,
                                bottom = 40.dp
                            )
                        }
                        "단모환" -> {
                            resizeImageView(283, 350)
                            setMargin(
                                top = 0.dp,
                                start = 0.dp,
                                end = 0.dp,
                                bottom = 40.dp
                            )
                        }
                        else -> {

                        }
                    }
                }
                else -> { // 3단계인 경우
                    if (dDay != null) {
                        if (dDay < 0) {
                            when (plantName) {
                                "민들레" -> {
                                    resizeImageView(235, 388)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "로즈마리" -> {
                                    resizeImageView(204, 500)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "아메리칸블루" -> {
                                    resizeImageView(204, 461)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "스투키" -> {
                                    resizeImageView(305, 440)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "단모환" -> {
                                    resizeImageView(283, 350)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                else -> {

                                }
                            }
                        } else { // dDay가 0이 아닌 경우
                            when (plantName) {
                                "민들레" -> {
                                    matchSizeImageView()
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 0.dp
                                    )
                                }
                                "로즈마리" -> {
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 0.dp
                                    )
                                    matchSizeImageView()
                                }
                                "아메리칸블루" -> {
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 0.dp
                                    )
                                    matchSizeImageView()
                                }
                                "스투키" -> {
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 0.dp
                                    )
                                    matchSizeImageView()
                                }
                                "단모환" -> {
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 0.dp
                                    )
                                    matchSizeImageView()
                                }
                                else -> {

                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["userNickname", "selectedCherishName"])
fun setReviewMainText(
    textView: TextView,
    userNickname: String?,
    selectedCherishName: String?
) {
    textView.text = "${userNickname}님!${selectedCherishName}와(과)의"
}

@BindingAdapter("setReviewSubText")
fun setReviewSubText(textView: TextView, selectedCherishName: String?) {
    textView.text = "${selectedCherishName}와(과)의 물주기를 기록해주세요"
}
@BindingAdapter("android:calendarAllowChange")
fun calendarAllowChange(imageButton: ImageButton, focus: Boolean) {
    if (focus) {
        imageButton.setImageResource(R.drawable.icn_allow_top)
    } else {
        imageButton.setImageResource(R.drawable.icn_allow)
    }
}
@BindingAdapter(
    value = ["android:userStatus1", "android:userStatus2", "android:userStatus3"],
    requireAll = false
)
fun showChips(
    flexboxLayout: FlexboxLayout,
    userStatus1: String?,
    userStatus2: String?,
    userStatus3: String?
) {
    flexboxLayout.clearChips()
    flexboxLayout.apply {
        if (userStatus1 != "null" && userStatus1 != " " && userStatus1 != "") {
            userStatus1?.let { addChipCalendar(it) }
        }
        if (userStatus2 != "null" && userStatus2 != " " && userStatus2 != "") {
            userStatus2?.let { addChipCalendar(it) }
        }
        if (userStatus3 != "null" && userStatus3 != " " && userStatus3 != "") {
            userStatus3?.let { addChipCalendar(it) }
        }
    }
}

@BindingAdapter("android:showMemo")
fun showMemo(textView: TextView, memoText: String?) {
    textView.text = " "
    textView.text = memoText
}

@BindingAdapter("android:showDate")
fun showDate(textView: TextView, date: CalendarDay?) {
    date?.let {
        textView.text = "${it.year}년 ${it.month}월 ${it.day}일"
    }
}

@BindingAdapter("android:wateringAnimation")
fun wateringAnimation(imageView: ImageView, isWatered: Boolean?) {
    val fadeAnimation =
        AnimationUtils.loadAnimation(imageView.context, R.anim.waterinf_fade_out)
    if (isWatered != null) {
        if (isWatered == true) {
            imageView.visibility = View.VISIBLE
            imageView.animation = fadeAnimation
            Glide.with(imageView.context)
                .asGif()
                .load(R.raw.watering)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
            val delayHandler = Handler(imageView.context.mainLooper)
            delayHandler.postDelayed({
                imageView.visibility = View.INVISIBLE
            }, 3500L)
        }
    }
}

@BindingAdapter(value = ["delayPlantIsWatered", "delayPlantGrowth", "delayPlantName"])
fun delayChangePlantImage(
    imageView: ImageView,
    delayPlantIsWatered: Boolean?,
    delayPlantGrowth: Int?,
    delayPlantName: String?
) {
    if (delayPlantIsWatered != null) {
        if (delayPlantIsWatered == false) {
            if (delayPlantGrowth != null) {
                when {
                    delayPlantGrowth <= 25 -> {
                        Glide.with(imageView)
                            .asDrawable()
                            .load(
                                when (delayPlantName) {
                                    "민들레" -> R.drawable.dandelion_grayshadow_1
                                    "로즈마리" -> R.drawable.rosemary_grayshadow_1
                                    "아메리칸블루" -> R.drawable.americanblue_grayshadow_1
                                    "스투키" -> R.drawable.stuckyi_grayshadow_1
                                    "단모환" -> R.drawable.cactus_grayshadow_1
                                    else -> R.drawable.img_white
                                }
                            )
                        imageView.apply {
                            when (delayPlantName) {
                                "민들레" -> {
                                    resizeImageView(262, 331)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "로즈마리" -> {
                                    resizeImageView(220, 380)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 44.dp
                                    )
                                }
                                "아메리칸블루" -> {
                                    resizeImageView(249, 368)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "스투키" -> {
                                    resizeImageView(295, 266.6.toInt())
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "단모환" -> {
                                    resizeImageView(275, 229)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 44.dp
                                    )
                                }
                                else -> {

                                }
                            }
                        }
                    }
                    delayPlantGrowth in 26..50 -> {
                        Glide.with(imageView)
                            .asDrawable()
                            .load(
                                when (delayPlantName) {
                                    "민들레" -> R.drawable.dandelion_grayshadow_2
                                    "로즈마리" -> R.drawable.rosemary_grayshadow_2
                                    "아메리칸블루" -> R.drawable.americanblue_grayshadow_2
                                    "스투키" -> R.drawable.stuckyi_grayshadow_2
                                    "단모환" -> R.drawable.cactus_grayshadow_2
                                    else -> R.drawable.img_white
                                }
                            )
                            .into(imageView)
                        imageView.apply {
                            when (delayPlantName) {
                                "민들레" -> {
                                    resizeImageView(235, 388)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "로즈마리" -> {
                                    resizeImageView(192, 500)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "아메리칸블루" -> {
                                    resizeImageView(204, 461)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 22.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "스투키" -> {
                                    resizeImageView(240, 313)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "단모환" -> {
                                    resizeImageView(283, 350)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                else -> {

                                }
                            }
                        }
                    }
                    else -> {
                        Glide.with(imageView)
                            .asDrawable()
                            .load(
                                when (delayPlantName) {
                                    "민들레" -> R.drawable.dandelion_grayshadow_3
                                    "로즈마리" -> R.drawable.rosemary_grayshadow_3
                                    "아메리칸블루" -> R.drawable.americanblue_grayshadow_3
                                    "스투키" -> R.drawable.stuckyi_grayshadow_3
                                    "단모환" -> R.drawable.cactus_grayshadow_3
                                    else -> R.drawable.img_white
                                }
                            )
                            .into(imageView)
                        imageView.apply {
                            when (delayPlantName) {
                                "민들레" -> {
                                    resizeImageView(235, 388)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "로즈마리" -> {
                                    resizeImageView(204, 500)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "아메리칸블루" -> {
                                    resizeImageView(204, 461)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "스투키" -> {
                                    resizeImageView(305, 440)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                "단모환" -> {
                                    resizeImageView(283, 350)
                                    setMargin(
                                        top = 0.dp,
                                        start = 0.dp,
                                        end = 0.dp,
                                        bottom = 40.dp
                                    )
                                }
                                else -> {

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@BindingAdapter("myPageday")
fun myPageday(textView: TextView,dDay: Int){
    if(dDay==0){
        textView.text = "D-Day"
        textView.setBackgroundResource(R.drawable.my_page_chip_red) //초록색으로
        textView.setTextColor(Color.parseColor("#F7596C"))
    }else if(dDay<0){
        textView.text="D+${abs(dDay)}"
        textView.setBackgroundResource(R.drawable.my_page_chip_red)
        textView.setTextColor(Color.parseColor("#F7596C"))
    }else{
        textView.text="D-${dDay}"
        textView.setBackgroundResource(R.drawable.my_page_chip_green) //초록색으로
        textView.setTextColor(Color.parseColor("#1AD287"))
    }

}

@BindingAdapter("btnStyle")
fun setBtnStyle(button: Button,isLock:Boolean){
    if(isLock){
        button.setBackgroundColor(Color.parseColor("#1AD287"))

    }
}