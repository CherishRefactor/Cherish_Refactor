package com.example.cherish_refactor.util.dialog

import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.DialogContactBinding
import com.example.cherish_refactor.ui.home.HomeViewModel
import com.example.cherish_refactor.util.ContextExtension.isInstalledApp
import com.example.cherish_refactor.util.ContextExtension.moveMarket
import com.example.cherish_refactor.util.FlexBoxExtension.addBlackChipModeChoice
import com.example.cherish_refactor.util.FlexBoxExtension.clearChips


class ContactDialogFragment : DialogFragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: DialogContactBinding

    lateinit var whereId:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.dialog_contact, container, false)
        binding = DialogContactBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        binding.dialogContact = this
        //viewModel.requestCalendar(4154)
        viewModel.fetchCalendarData()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setChip()
        return binding.root
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if(tag=="detail"){
            whereId="detail"
        }else{
            whereId="home"
        }
        super.show(manager, tag)
    }


    private fun setChip() {
        viewModel.selectedCherishUser.observe(viewLifecycleOwner) {
            Log.d("fffff", it.toString())


        }
        viewModel.selectedDay.observe(viewLifecycleOwner) {
            binding.contactChipLayout.apply {
                clearChips()
                if (it.userStatus1 != "" && it.userStatus1 != "null")
                    addBlackChipModeChoice(it.userStatus1)
                if (it.userStatus2 != "" && it.userStatus2 != "null")
                    addBlackChipModeChoice(it.userStatus2)
                if (it.userStatus3 != "" && it.userStatus3 != "null")
                    addBlackChipModeChoice(it.userStatus3)
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
        //doAfterConfirm()
        if(whereId=="detail"){
            findNavController().navigate(R.id.action_detailPlantFragment_to_reviewFragment)
            dismiss()
        }else{
            findNavController().navigate(R.id.action_main_home_to_reviewFragment)
            dismiss()
        }



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
        private const val codeThatDetailReviewPage = 1001
        private const val codeThatGetWatering = 1002
    }
}