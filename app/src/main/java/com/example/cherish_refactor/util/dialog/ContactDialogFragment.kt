package com.example.cherish_refactor.util.dialog

import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.DialogContactBinding
import com.example.cherish_refactor.ui.home.HomeViewModel
import com.example.cherish_refactor.util.ContextExtension.isInstalledApp
import com.example.cherish_refactor.util.ContextExtension.moveMarket
import com.example.cherish_refactor.util.FlexBoxExtension.addBlackChipModeChoice
import com.example.cherish_refactor.util.FlexBoxExtension.clearChips


class ContactDialogFragment : DialogFragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.dialog_contact, container, false)
        val binding= DialogContactBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        binding.dialogContact = this
        viewModel.requestCalendar()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setChip(binding)
        return binding.root
    }


    private fun setChip(binding: DialogContactBinding) {
        viewModel.calendarData.observe(viewLifecycleOwner) {
            binding.contactChipLayout.apply {
                clearChips()
                if (it?.waterData?.calendarData?.isNullOrEmpty()!!) {
                    it.waterData.calendarData.let { calendarData ->
                        if (calendarData.last().userStatus1 != "" && calendarData.last().userStatus1 != "null")
                            addBlackChipModeChoice(it.waterData.calendarData.last().userStatus1)
                        if (calendarData.last().userStatus2 != "" && calendarData.last().userStatus2 != "null")
                            addBlackChipModeChoice(it.waterData.calendarData.last().userStatus2)
                        if (calendarData.last().userStatus3 != "" && calendarData.last().userStatus3 != "null")
                            addBlackChipModeChoice(it.waterData.calendarData.last().userStatus3)
                    }
                }
            }
        }
    }

    fun navigateCall() {
/*        if (PermissionUtil.isCheckedCallPermission(requireContext())) {

        } else {
            PermissionUtil.openPermissionSettings(requireContext())
        }*/

        startPhoneCall()
    }

    fun navigateToSendMessage() {
/*        if (PermissionUtil.isCheckedSendMessagePermission(requireContext())) {

        } else {
            PermissionUtil.openPermissionSettings(requireContext())
        }*/

        startSendMessage()
    }

    fun navigateKakao() {
        if (requireContext().isInstalledApp(KAKAO_PACKAGE_NAME)) {
            val kakaoIntent = requireContext().packageManager.getLaunchIntentForPackage(
                KAKAO_PACKAGE_NAME
            )
            kakaoIntent!!.flags = FLAG_ACTIVITY_REORDER_TO_FRONT
            startReview()
            startActivity(kakaoIntent)
        } else {
            requireContext().moveMarket(KAKAO_PACKAGE_NAME)
        }
        sendRemindReviewNotification()
    }

    private fun startPhoneCall() {
        val callIntent =
            Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:${viewModel.selectedCherishUser.value!!.phoneNumber}")
            )
        startActivityForResult(
            callIntent,
            codeThatReviewPage
        )
        sendRemindReviewNotification()
    }

    private fun startSendMessage() {
        val messageIntent = Intent(
            Intent.ACTION_SENDTO,
            Uri.parse("smsto:${viewModel.selectedCherishUser.value!!.phoneNumber}")
        )
        startActivityForResult(
            messageIntent,
            codeThatReviewPage
        )
        sendRemindReviewNotification()
    }

    private fun startReview() {

    }

    private fun sendRemindReviewNotification() {
       // viewModel.sendRemindReview(NotificationRemindReviewReq(viewModel.selectedCherishUser.value!!.id))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == codeThatReviewPage) {
            if (resultCode == RESULT_CANCELED) {
                startReview()
            }
        }
        /*if (requestCode == codeThatGetWatering) {
            if (resultCode == RESULT_OK) {
                viewModel.isWatered.value = data?.getBooleanExtra("wateringTrigger", false)
                dismiss()
            }
        }*/
    }

    companion object {
        private const val KAKAO_PACKAGE_NAME = "com.kakao.talk"
        private const val codeThatReviewPage = 1001
        private const val codeThatGetWatering = 1002
    }
}