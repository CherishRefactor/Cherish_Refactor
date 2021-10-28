package com.example.cherish_refactor.ui.detail.popup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cherish_refactor.data.source.remote.api.DetailPopUpResponse
import com.example.cherish_refactor.databinding.DialogPopUpBinding

class DialogAdapter : RecyclerView.Adapter<DialogAdapter.DialogViewHolder>() {

    private var item= mutableListOf<DetailPopUpResponse.Data.Plant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        val binding =
            DialogPopUpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DialogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DialogViewHolder, position: Int) {
        holder.bind(item[position],position)
    }

    override fun getItemCount() = item.size

    fun setDetail(detail: List<DetailPopUpResponse.Data.Plant>){
        item= detail as MutableList<DetailPopUpResponse.Data.Plant>
        notifyDataSetChanged()
    }



    inner class DialogViewHolder(private val binding: DialogPopUpBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(detail: DetailPopUpResponse.Data.Plant, position: Int) {

            Glide.with(binding.root.context)
                .load(detail.image_url)
                .into(binding.flowerImg)
            binding.flowerName.text = detail.modifier
            binding.chip.text = "꽃말 | " + detail.flower_meaning
            binding.wateringText.text = detail.explanation
        }




    }


}