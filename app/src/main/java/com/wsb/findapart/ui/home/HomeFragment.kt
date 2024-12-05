package com.wsb.findapart.ui.home

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.wsb.findapart.R

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.text_home)

        viewModel = ViewModelProvider(this).get<HomeViewModel>()
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}