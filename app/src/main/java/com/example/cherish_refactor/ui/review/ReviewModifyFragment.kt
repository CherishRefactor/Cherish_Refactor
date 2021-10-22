package com.example.cherish_refactor.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.FragmentReviewModifyBinding
import com.example.cherish_refactor.ui.base.BaseFragment
import com.example.cherish_refactor.ui.home.HomeViewModel
import com.example.cherish_refactor.util.DateUtil
import com.example.cherish_refactor.util.FlexBoxExtension.addChip
import com.example.cherish_refactor.util.FlexBoxExtension.getChip
import com.example.cherish_refactor.util.countNumberOfCharacters
import com.example.cherish_refactor.util.writeKeyword


class ReviewModifyFragment : BaseFragment<FragmentReviewModifyBinding>(R.layout.fragment_review_modify) {

    private val viewModel: HomeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreateView(inflater, container, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm=viewModel
        setHasOptionsMenu(true)


        observeSelectedDate()
        addLimitNumberOfMemoCharacters()
        addLimitNumberOfKeywordCharacters()
        addUserStatusWithChip()
        setListener()

        return binding.root
    }

    fun setListener(){
        binding.reviseReviewAdminAccept.setOnClickListener {

            viewModel.sendReviewModify(binding.reviseReviewFlexBox.getChip(0)?.text.toString(),
                binding.reviseReviewFlexBox.getChip(1)?.text.toString(),binding.reviseReviewFlexBox.getChip(2)?.text.toString(),binding.reviseReviewMemo.text.toString())

            findNavController().navigate(R.id.action_reviewModifyFragment_to_calendarFragment)
        }
    }

    private fun observeSelectedDate() {
        viewModel.selectedDay.observe(viewLifecycleOwner) {
            binding.reviseReviewDateText.text = it?.wateredDate?.let { it1 ->
                DateUtil.convertDateToString(
                    it1
                )
            }
            binding.reviseReviewFlexBox.apply {
                it?.userStatus1?.let { userStatus1 ->
                    if (userStatus1 != "null" && userStatus1 != "" && userStatus1 != " ") addChip(
                        userStatus1
                    )
                }
                it?.userStatus2?.let { userStatus2 ->
                    if (userStatus2 != "null" && userStatus2 != "" && userStatus2 != " ") addChip(
                        userStatus2
                    )
                }
                it?.userStatus3?.let { userStatus3 ->
                    if (userStatus3 != "null" && userStatus3 != "" && userStatus3 != " ") addChip(
                        userStatus3
                    )
                }
            }
            binding.reviseReviewMemo.setText(it?.review)
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbReviewModify.inflateMenu(R.menu.toolbar_menu)
        binding.tbReviewModify.menu.findItem(R.id.setting).isVisible = false
        binding.tbReviewModify.menu.findItem(R.id.calendar).isVisible = false
        binding.tbReviewModify.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.trash ->{

                    true
                }
                else -> false
            }

        }

    }

    private fun addLimitNumberOfMemoCharacters() {
        binding.reviseReviewMemo.countNumberOfCharacters { memo ->
            binding.reviseReviewNumberOfMemo.text = memo?.length.toString()
        }
    }

    private fun addLimitNumberOfKeywordCharacters() {
        binding.reviseReviewEditKeyword.countNumberOfCharacters { keyword ->
            binding.reviseReviewNumberOfCharacters.text = keyword?.length.toString()
        }
    }

    private fun addUserStatusWithChip() {
        binding.reviseReviewEditKeyword.writeKeyword(
            binding.reviseReviewFlexBox,
            parentFragmentManager
        )
    }

    companion object {
        const val TAG = "reviseReviewFragment"
    }

}