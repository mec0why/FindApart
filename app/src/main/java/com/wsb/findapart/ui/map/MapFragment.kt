package com.wsb.findapart.ui.map

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.wsb.findapart.R

class MapFragment : Fragment(R.layout.fragment_map) {
    private lateinit var viewModel: MapViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.text_map)

        viewModel = ViewModelProvider(this).get<MapViewModel>()
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}