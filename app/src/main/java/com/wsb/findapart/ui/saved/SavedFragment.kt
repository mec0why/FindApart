package com.wsb.findapart.ui.saved

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.wsb.findapart.R

class SavedFragment : Fragment(R.layout.fragment_saved) {
    private lateinit var viewModel: SavedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.text_saved)

        viewModel = ViewModelProvider(this).get<SavedViewModel>()
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}