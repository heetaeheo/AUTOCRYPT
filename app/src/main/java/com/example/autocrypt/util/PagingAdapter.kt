package com.example.autocrypt.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.autocrypt.R
import com.example.autocrypt.data.response.CenterDataResponse
import com.example.autocrypt.databinding.ViewholderCenterBinding

class PagingAdapter:
    PagingDataAdapter<CenterDataResponse, PagingAdapter.PagingViewHolder>(PAGE_DIFF)
{

    inner class PagingViewHolder(
        private val binding: ViewholderCenterBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CenterDataResponse){
            with(binding){
                binding.id.text = item.id.toString()
                binding.centerName.text = item.centerName.toString()
                binding.address.text = item.address.toString()
                binding.lat.text = item.lat.toString()
                binding.lng.text = item.lng.toString()
                binding.updatedAt.text = item.updatedAt.toString()
                binding.facilityName.text = item.facilityName.toString()

                binding.executePendingBindings()
            }
        }

    }

    companion object {
        private val PAGE_DIFF = object : DiffUtil.ItemCallback<CenterDataResponse>() {
            override fun areItemsTheSame(oldItem: CenterDataResponse, newItem: CenterDataResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CenterDataResponse, newItem: CenterDataResponse): Boolean {
                return oldItem == newItem
            }
        }
    }



    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        getItem(position)?.let{holder.bind(it)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder
        = PagingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.viewholder_center,parent,false))

}