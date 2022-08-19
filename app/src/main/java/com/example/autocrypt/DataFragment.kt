package com.example.autocrypt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.autocrypt.data.db.CenterDataEntity
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
    lateinit var adatper : PagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataBinding.inflate(layoutInflater)
        return binding.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adatper = PagingAdapter()
        observer()
        initAdapter()

    }

    private fun initAdapter(){
        binding.recycler.adapter = adatper
        binding.recycler.layoutManager = GridLayoutManager(
            requireContext(),
            1,
            GridLayoutManager.VERTICAL,
            false
        )
    }
    lateinit var markers : List<CenterDataEntity>

    private fun observer() = with(viewModel){
        lifecycleScope.launch{
            getData().collectLatest {
                adatper.submitData(it)
            }
            getRoomData()
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