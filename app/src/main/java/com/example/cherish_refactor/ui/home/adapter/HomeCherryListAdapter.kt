package com.example.cherish_refactor.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cherish_refactor.data.source.remote.api.User
import com.example.cherish_refactor.databinding.MainCherryItemBinding


class HomeCherryListAdapter: RecyclerView.Adapter<HomeCherryListAdapter.MainViewHolder>() {

    var data = mutableListOf<User>()
    var onClick: ((User,Int) -> Unit)? = null

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
        data= userList as MutableList<User>
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val binding: MainCherryItemBinding, onClick: ((User,Int) -> Unit)?) :
        RecyclerView.ViewHolder(binding.root) {
        init{binding.onClick = onClick}

        fun bind(userData: User,position: Int) {
            binding.user=userData

            binding.position=position

        }
    }


}
