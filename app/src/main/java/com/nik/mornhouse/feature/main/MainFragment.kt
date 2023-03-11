package com.nik.mornhouse.feature.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nik.mornhouse.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val viewModel by viewModels<MainViewModel>()
    private val adapter = NumberFactAdapter { fact ->
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(fact))

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

            viewModel.factTextLiveData.observe(viewLifecycleOwner) { fact ->
                tvText.text = fact
            }

            getRandomFactButton.setOnClickListener {
                val number = (1..1000).random()
                numberEdittext.apply {
                    setText("")
                    setText(number.toString())
                }
            }
            getFactButton.setOnClickListener {
                val numberStr = numberEdittext.text.toString()
                if (numberStr.isBlank()) {
                    numberEdittext.error = "You should write number"
                    return@setOnClickListener
                }
                val number = numberStr.toLong()
                Log.d("Nik", "Getting fact for number: $number")
                viewModel.viewModelScope.launch {
                    val fact = viewModel.getNumberFact(number)
                    viewModel.setFactText(fact)
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
