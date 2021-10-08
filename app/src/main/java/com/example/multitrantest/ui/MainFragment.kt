package com.example.multitrantest.ui

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multitrantest.R
import com.example.multitrantest.adapters.TranslationsAdapter
import com.example.multitrantest.databinding.MainFragmentBinding
import com.example.multitrantest.viewmodels.MainViewModel


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: MainFragmentBinding

    private var transAdapter: TranslationsAdapter = TranslationsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLanAdapter()

        binding.spinner1.apply {
            adapter = initLanAdapter()
            onItemSelectedListener = initListener {
                viewModel.setLan1(it)
            }
        }

        binding.spinner2.apply {
            adapter = initLanAdapter()
            onItemSelectedListener = initListener {
                viewModel.setLan2(it)
            }
        }

        binding.translateButton.setOnClickListener {
            viewModel.setWord(binding.editText.text.toString())
            viewModel.translate()
        }

        viewModel.translations.observe(viewLifecycleOwner, {
            transAdapter.setList(it)
            binding.translationList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = transAdapter
            }
        })

        binding.historyButton.setOnClickListener {
            val nextFrag = HistoryFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initLanAdapter(): ArrayAdapter<CharSequence> {
        return ArrayAdapter.createFromResource(
            requireContext(),
            R.array.languages_array,
            R.layout.my_spinner_textview
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.my_spinner_textview)
        }
    }

    private fun initListener(setLan: (Int) -> Unit): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.getLanId()[parent.getItemAtPosition(position) as String]?.let {
                    setLan(it)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                setLan(1)
            }
        }
    }

}