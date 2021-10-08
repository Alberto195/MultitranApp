package com.example.multitrantest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multitrantest.adapters.HistoryAdapter
import com.example.multitrantest.adapters.TranslationsAdapter
import com.example.multitrantest.databinding.HistoryFragmentBinding
import com.example.multitrantest.databinding.MainFragmentBinding
import com.example.multitrantest.viewmodels.MainViewModel

class HistoryFragment: Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: HistoryFragmentBinding
    private var historyAdapter: HistoryAdapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HistoryFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.history.observe(viewLifecycleOwner, {
            historyAdapter.setList(it)
            binding.historyRecycler.adapter = historyAdapter
        })
        binding.historyRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }

        viewModel.getHistory()
    }
}
