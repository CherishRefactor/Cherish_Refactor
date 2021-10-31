package com.example.cherish_refactor.ui.manage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cherish_refactor.data.source.remote.api.ManageResponse
import com.example.cherish_refactor.databinding.MyPageCherryItemBinding

class PlantAdapter :
    RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    private var item = mutableListOf<ManageResponse.MyPageUserData.MyPageCherishData>()

    inner class ViewHolder(private val binding: MyPageCherryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(myPageCherishData: ManageResponse.MyPageUserData.MyPageCherishData) {
                binding.my=myPageCherishData
            }
    }



    fun setManage(manage:List<ManageResponse.MyPageUserData.MyPageCherishData>){
        item = manage as MutableList<ManageResponse.MyPageUserData.MyPageCherishData>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MyPageCherryItemBinding =
            MyPageCherryItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = item.size

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}