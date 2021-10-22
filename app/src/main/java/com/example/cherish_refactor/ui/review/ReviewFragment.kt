package com.example.cherish_refactor.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentReviewBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.home.HomeViewModel
import com.example.cherish_refactor.util.FlexBoxExtension.getChip
import com.example.cherish_refactor.util.FlexBoxExtension.getChipsCount
import com.example.cherish_refactor.util.countNumberOfCharacters
import com.example.cherish_refactor.util.dialog.MultiViewDialog
import com.example.cherish_refactor.util.writeKeyword
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ReviewFragment : BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {

    private val viewModel: HomeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        setListener()
        //reviewNotificationViewModel.startNotificationTimer()


        addUserStatusWithChip()
        addLimitNumberOfKeywordCharacters()
        addLimitNumberOfMemoCharacters()


        return binding.root
    }

    private fun addLimitNumberOfMemoCharacters() {
        binding.homeReviewMemo.countNumberOfCharacters { memo ->
            binding.homeReviewNumberOfMemo.text = memo?.length.toString()
        }
    }

    private fun addLimitNumberOfKeywordCharacters() {
        binding.homeReviewEditKeyword.countNumberOfCharacters { keyword ->
            binding.homeReviewNumberOfCharacters.text = keyword?.length.toString()
        }
    }

    private fun showLoadingDialog() {
        lifecycleScope.launch {
            val dialog = MultiViewDialog(R.layout.dialog_loading, 0.35f, 0.169f)
            dialog.show(parentFragmentManager, TAG)
            delay(2000)
            dialog.dismiss()
            //viewModel.isWatered.value = true
            //parentFragmentManager.beginTransaction().remove(this@ReviewFragment).commit()
            //viewModel.delayFetchUsers()
            //parentFragmentManager.popBackStack()

            findNavController().navigate(R.id.action_reviewFragment_to_main_home)
        }
    }

    private fun addUserStatusWithChip() {
        binding.homeReviewEditKeyword.writeKeyword(binding.homeReviewFlexBox, parentFragmentManager)
    }

    private fun setListener() {
        binding.homeReviewIgnoreAccept.setOnClickListener {
            //reviewNotificationViewModel.cancel()
            //viewModel.remindReviewNotificationToServer(viewModel.selectedCherishUser.value!!.id)
            viewModel.sendReviewToServer("","","")

            showLoadingDialog()
        }
        binding.homeReviewAdminAccept.setOnClickListener {
            if (binding.homeReviewFlexBox.getChipsCount() == 0 && binding.homeReviewMemo.text.isEmpty()) {
                MultiViewDialog(
                    R.layout.dialog_warning_review_no_word_warning,
                    0.6944f,
                    0.16875f
                ).show(
                    parentFragmentManager,
                    TAG
                )
            } else {
                if (binding.homeReviewMemo.text.length <= 100) {

                    viewModel.sendReviewToServer(
                            if (binding.homeReviewFlexBox.getChip(0) == null) "" else binding.homeReviewFlexBox.getChip(
                                0
                            )!!.text.toString(),
                            if (binding.homeReviewFlexBox.getChip(1) == null) "" else binding.homeReviewFlexBox.getChip(
                                1
                            )!!.text.toString(),
                            if (binding.homeReviewFlexBox.getChip(2) == null) "" else binding.homeReviewFlexBox.getChip(
                                2
                            )!!.text.toString(),

                    )
                    showLoadingDialog()
                } else {
                    MultiViewDialog(
                        R.layout.dialog_warning_review_limit_error,
                        0.6944f,
                        0.16875f
                    ).show(
                        parentFragmentManager,
                        TAG
                    )
                }
            }
        }
    }



    companion object {
        const val TAG = "ReviewFragment"
    }
}