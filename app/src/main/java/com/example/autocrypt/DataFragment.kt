package com.example.autocrypt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.autocrypt.databinding.FragmentDataBinding
import com.example.autocrypt.util.PagingAdapter
import com.example.autocrypt.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DataFragment : Fragment() {

    private lateinit var binding : FragmentDataBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = PagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observer()
    }

    private fun initAdapter(){
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(
            requireContext(),
            1,
            GridLayoutManager.VERTICAL,
            false
        )

    }



    private fun observer(){
        lifecycleScope.launch{
            viewModel.getData().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    companion object {
        const val TAG= "DataFragment"

        fun newInstance(): DataFragment{
            return DataFragment().apply {

            }
        }
    }

}