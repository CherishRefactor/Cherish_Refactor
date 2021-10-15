package com.example.cherish_refactor.ui.enroll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentEnrollPlantBinding
import com.example.cherish_refactor.ui.base.BaseFragment


class EnrollPlantFragment : BaseFragment<FragmentEnrollPlantBinding>(R.layout.fragment_enroll_plant) {


    private val enrollmentViewModel :EnrollmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm=enrollmentViewModel


        /*binding.detailOkBtn.setOnClickListener {
            //  progressON()
//이름
            if (binding.editNick.text.isEmpty()) {
                usernickname = binding.editNick.hint.toString()
                username = binding.editNick.hint.toString()
            } else {
                usernickname = binding.editNick.text.toString()
                username = arguments?.getString("phonename").toString()
            }
//애칭
            if (binding.editBirth.text.isNotEmpty()) {
                userbirth = binding.editBirth.text.toString()
            } else {
                userbirth = "00/00"

            }
//생일
            val userphone = arguments?.getString("phonenumber")
            val userwater = binding.waterAlarmWeek.text.split(" ")[1]

            val usertime = binding.waterAlarmTime.text.toString()
            val usertime_hour = usertime.substring(0, 5).toString()

            val userid = arguments?.getInt("useridend").toString()
            val body =
                RequestEnrollData(
                    name = username.toString(),
                    nickname = usernickname,
                    birth = userbirth,
                    phone = userphone!!,
                    cycle_date = userwater.toInt(),
                    notice_time = usertime_hour,
                    water_notice = true,
                    UserId = userid.toInt()

                )

            requestData.enrollAPI.enrollCherish(body)
                .enqueue(
                    object : Callback<ResponseEnrollData> {
                        override fun onFailure(
                            call: Call<ResponseEnrollData>,
                            t: Throwable
                        ) {
                            Log.d("통신 실패", t.toString())
                        }

                        override fun onResponse(
                            call: Call<ResponseEnrollData>,
                            response: Response<ResponseEnrollData>
                        ) {
                            response.takeIf {
                                it.isSuccessful
                            }?.body()
                                ?.let { it ->
                                    plant_id = it.data.plant.PlantStatusId
                                    plant_explanation = it.data.plant.explanation
                                    plant_modify = it.data.plant.modifier
                                    plant_mean = it.data.plant.flower_meaning
                                    plant_url = it.data.plant.image_url


                                }
                        }
                    }
                )

            progressON()
            Handler(Looper.getMainLooper()).postDelayed({
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.fragment_enroll,
                    ResultPlantFragment().apply {
                        arguments = Bundle().apply {
                            //putString("plantkey", binding.waterAlarmWeek.text.toString())
                            putString(
                                "plant_explanation",
                                plant_explanation
                            )
                            putString(
                                "plant_modify",
                                plant_modify
                            )
                            putString(
                                "plant_mean",
                                plant_mean
                            )
                            putString("plant_url", plant_url)
                            putInt("plant_id", plant_id)

                        }
                    })
                progressOFF()
                transaction.addToBackStack(null)

                transaction.commit()

            }, 4000)
        }
*/
        /*binding.editBirth.setOnClickListener {
            BirthPickerDialogFragment(R.layout.birthpicker_layout).show(
                parentFragmentManager,
                "MainActivity"
            )
        }
        //timepicker 나오는 부분
        binding.editclock.setOnClickListener {
            val needWaterDialog = ClockPickerDialogFragment(R.layout.clockpicker_layout).show(
                parentFragmentManager,
                "MainActivity"
            )

        }

        binding.editweek.setOnClickListener {

            val needweek = WeekPickerDialogFragment(R.layout.weekpicker_layout).show(
                parentFragmentManager,
                "MainActivity"
            )


        }*/


        return binding.root
    }





}