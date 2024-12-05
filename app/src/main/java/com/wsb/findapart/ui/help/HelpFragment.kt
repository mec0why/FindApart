package com.wsb.findapart.ui.help

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.wsb.findapart.R

class HelpFragment : Fragment(R.layout.fragment_help) {
    private lateinit var viewModel: HelpViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.text_help)

        viewModel = ViewModelProvider(this).get<HelpViewModel>()
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}