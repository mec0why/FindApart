package com.wsb.findapart.ui.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.wsb.findapart.R
import com.wsb.findapart.databinding.FragmentMapBinding

class MapFragment : Fragment(R.layout.fragment_map) {
    private lateinit var viewModel: MapViewModel
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMapBinding.bind(view)

        val textView = binding.textMap

        viewModel = ViewModelProvider(this).get<MapViewModel>()
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}