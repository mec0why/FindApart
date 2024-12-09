package com.wsb.findapart.ui.saved

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.wsb.findapart.R
import com.wsb.findapart.databinding.FragmentSavedBinding

class SavedFragment : Fragment(R.layout.fragment_saved) {
    private lateinit var viewModel: SavedViewModel
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSavedBinding.bind(view)

        val textView = binding.textSaved

        viewModel = ViewModelProvider(this).get<SavedViewModel>()
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}