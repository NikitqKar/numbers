package com.nik.mornhouse.feature.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import com.nik.mornhouse.databinding.FragmentDetailBinding
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(inflater, container, false).apply {
        binding = this
        tvDeteiledFact.text = args.number.text
        tvDeteiledNumber.text = args.number.number.toString()

    }.root


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}