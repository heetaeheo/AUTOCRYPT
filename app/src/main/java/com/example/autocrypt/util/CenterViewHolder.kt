package com.example.autocrypt.util

import androidx.recyclerview.widget.RecyclerView
import com.example.autocrypt.databinding.ViewholderCenterBinding
import com.example.autocrypt.viewModel.MainViewModel

class CenterViewHolder(
    private val binding : ViewholderCenterBinding,
    viewModel:MainViewModel,
): RecyclerView.ViewHolder(binding.root) {
}