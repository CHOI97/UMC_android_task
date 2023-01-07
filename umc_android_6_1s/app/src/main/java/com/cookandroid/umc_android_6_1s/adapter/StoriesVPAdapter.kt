package com.cookandroid.umc_android_6_1s.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.umc_android_6_1s.databinding.ItemStoriesVpBinding
import java.net.URL

class StoriesVPAdapter(private val dataList:ArrayList<Int>) :
    RecyclerView.Adapter<StoriesVPAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding: ItemStoriesVpBinding):RecyclerView.ViewHolder(viewBinding.root){
        fun bind(data: Int) {
            Glide.with(this.itemView).load(data).into(viewBinding.ivImage)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesVPAdapter.DataViewHolder {
        val viewBinding = ItemStoriesVpBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: StoriesVPAdapter.DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
    override fun getItemCount(): Int= dataList.size

}