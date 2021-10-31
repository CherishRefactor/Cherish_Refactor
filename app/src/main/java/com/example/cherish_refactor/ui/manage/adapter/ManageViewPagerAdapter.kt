package com.example.cherish_refactor.ui.manage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.cherish_refactor.ui.manage.PlantFragment
import com.example.cherish_refactor.ui.manage.PlantPhoneFragment

class ManageViewPagerAdapter(fragment : FragmentActivity): FragmentStateAdapter(fragment)
{
    private val fragmentList = ArrayList<Fragment>()
    var radio=false
    var search = false

    /*override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    fun addFragment(fragment: Fragment)
    {
        fragmentList.add(fragment)

    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
*/
    fun setRadioVisi(view: Boolean){
        radio=view
        notifyDataSetChanged()

    }

    fun setSearchVisi(view: Boolean){
        search=view
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)


    }


    override fun getItemCount(): Int =2


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                if(search==true){
                    PlantFragment(true)
                }else {
                    PlantFragment(false)
                }
            }
            else -> {
                if(radio==true){
                    PlantPhoneFragment(true)
                }else{
                    PlantPhoneFragment(false)
                }

            }
        }
    }


}