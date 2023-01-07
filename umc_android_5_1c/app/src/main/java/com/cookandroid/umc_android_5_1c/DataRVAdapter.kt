package com.cookandroid.umc_android_5_1c

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cookandroid.umc_android_5_1c.databinding.ItemDataBinding
class DataRVAdapter(private val dataList:ArrayList<Data>): RecyclerView.Adapter<DataRVAdapter.DataViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(v: View, data: Data, position: Int)
        fun onLongClick(v: View, data:Data,position: Int)
    }
    private var listener : OnItemClickListener? =null
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
    // ViewHolder 객체
    inner class DataViewHolder(private val viewBinding: ItemDataBinding):RecyclerView.ViewHolder(viewBinding.root){
        fun bind(data: Data,position: Int) {
            Log.d("this status", "bind")
            viewBinding.tvItemDesc.text = data.desc
        }
    }
    //ViewHolder 만들어 질때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        Log.d("this status","onCreateViewHolder")
        val viewBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(viewBinding)
    }
    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수 , 데이터를 표시할때마다 호출
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        Log.d("this status","onBindViewHolder.....position is $position")
        holder.bind(dataList[position],position)
        if(position != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView,dataList[position],position)
            }
            holder.itemView.setOnLongClickListener {
                listener?.onLongClick(holder.itemView,dataList[position],position)
                return@setOnLongClickListener true
            }

        }
    }
    // 표현할 Item의 총 개수
    override fun getItemCount(): Int= dataList.size

    companion object{
        const val SAVE = 1001
        const val FIX = 1002
    }

}