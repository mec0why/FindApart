package com.wsb.findapart.ui.list

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.wsb.findapart.R

class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var viewModel: ListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.text_list)

        viewModel = ViewModelProvider(this).get<ListViewModel>()
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}