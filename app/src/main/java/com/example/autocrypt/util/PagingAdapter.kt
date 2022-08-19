package com.example.autocrypt.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.autocrypt.R
import com.example.autocrypt.data.db.CenterDataEntity
import com.example.autocrypt.databinding.ViewholderCenterBinding
import kotlinx.coroutines.runBlocking


class PagingAdapter() :
    PagingDataAdapter<CenterDataEntity, PagingAdapter.PagingViewHolder>(PAGE_DIFF) {

    inner class PagingViewHolder(
        private val binding: ViewholderCenterBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CenterDataEntity) = runBlocking {
            with(binding) {

                id.text = item.id.toString()
                centerName.text = item.centerName.toString()
                address.text = item.address.toString()
                lat.text = item.lat.toString()
                lng.text = item.lng.toString()
                updatedAt.text = item.updatedAt.toString()
                facilityName.text = item.facilityName.toString()

                binding.executePendingBindings()
            }
        }


    }
    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder =
        PagingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.viewholder_center,
                parent,
                false
            )

        )
    companion object {
        private val PAGE_DIFF = object : DiffUtil.ItemCallback<CenterDataEntity>() {
            override fun areItemsTheSame(
                oldItem: CenterDataEntity,
                newItem: CenterDataEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CenterDataEntity,
                newItem: CenterDataEntity
            ): Boolean {
                return oldItem == newItem
            }
        }

    }


}