package com.nik.mornhouse.feature.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nik.mornhouse.data.entity.NumberFact
import com.nik.mornhouse.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val viewModel by viewModels<MainViewModel>()
    private val adapter = NumberFactAdapter { fact ->

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding?.apply {
            rcView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = this@MainFragment.adapter
            }

            viewModel.itemsLiveData.observe(viewLifecycleOwner) { facts ->
                adapter.setItems(facts)
            }

            getFactButton.setOnClickListener {
                val numberStr = numberEdittext.text.toString()
                if (numberStr.isBlank()) {
                    numberEdittext.error = "You should write number"
                    return@setOnClickListener
                }
                val number = numberStr.toInt()
                viewModel.viewModelScope.launch {
                    val fact = viewModel.getNumberFact(number)
                    textView.text = fact
                    viewModel.factText = fact
                }
            }

            getRandomFactButton.setOnClickListener {
                val number = (1..1000).random()
                viewModel.viewModelScope.launch {
                    val fact = viewModel.getNumberFact(number)
                    textView.text = fact
                    viewModel.factText = fact
                }
            }
        }
        return binding!!.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}