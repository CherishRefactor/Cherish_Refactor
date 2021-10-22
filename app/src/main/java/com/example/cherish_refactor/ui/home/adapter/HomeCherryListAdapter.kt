package com.example.cherish_refactor.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cherish_refactor.data.source.remote.api.User
import com.example.cherish_refactor.databinding.MainCherryItemBinding


class HomeCherryListAdapter: RecyclerView.Adapter<HomeCherryListAdapter.MainViewHolder>() {

    var data = mutableListOf<User>()
    var onClick: ((User,Int) -> Unit)? = null
    var seletedPosition =1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MainCherryItemBinding =
            MainCherryItemBinding.inflate(layoutInflater, parent, false)
        return MainViewHolder(binding,onClick)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position],position)
    }

    override fun getItemCount(): Int = data.size

    fun setItem(userList:List<User>){
        data.clear()
        data.add(0,userList[0])
        userList.forEach {
            data.add(it)
        }
       // data= userList as MutableList<User>
        //data[0]=userFirst
        notifyDataSetChanged()
    }

    fun setOp(position: Int){

    }


    inner class MainViewHolder(private val binding: MainCherryItemBinding, onClick: ((User,Int) -> Unit)?) :
        RecyclerView.ViewHolder(binding.root) {
        init{binding.onClick = onClick}



        fun bind(userData: User,position: Int) {
            binding.user=userData

            binding.position=position

            if (position == 0) {
                binding.homeItemFirstSelector.visibility = View.VISIBLE
            } else {
                binding.homeItemFirstSelector.visibility = View.INVISIBLE
            }
            Log.d("position",binding.position.toString())
            if(binding.position==seletedPosition){
                binding.apply {
                    mainUserImg.alpha = 0.2f
                    mainUserWater.alpha = 0.2f
                    mainUserName.alpha = 0.2f
                }
            }else {
                binding.apply {
                    mainUserImg.alpha = 1.0f
                    mainUserWater.alpha = 1.0f
                    mainUserName.alpha = 1.0f
                }
            }

           // bindOp(binding,adapterPosition)

        }

        fun bindOp(binding: MainCherryItemBinding,position: Int){
            Log.d("position",position.toString())
            Log.d("position2222",seletedPosition.toString())
            if (position == seletedPosition-1) {
                binding.apply {
                    mainUserImg.alpha = 0.2f
                    mainUserWater.alpha = 0.2f
                    mainUserName.alpha = 0.2f
                }
            } else {
                binding.apply {
                    mainUserImg.alpha = 1.0f
                    mainUserWater.alpha = 1.0f
                    mainUserName.alpha = 1.0f
                }
            }
        }
    }


}
