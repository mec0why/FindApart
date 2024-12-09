package com.wsb.findapart.ui.help

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.wsb.findapart.R
import com.wsb.findapart.databinding.FragmentHelpBinding

class HelpFragment : Fragment(R.layout.fragment_help) {
    private lateinit var viewModel: HelpViewModel
    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHelpBinding.bind(view)

        val textView = binding.textHelp

        viewModel = ViewModelProvider(this).get<HelpViewModel>()
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}