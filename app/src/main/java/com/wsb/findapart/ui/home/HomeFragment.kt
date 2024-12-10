package com.wsb.findapart.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.wsb.findapart.R
import com.wsb.findapart.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val cityOptions = resources.getStringArray(R.array.city_options).toList()
        val typeOptions = resources.getStringArray(R.array.type_options).toList()
        val areaOptions = resources.getStringArray(R.array.area_options).toList()
        val roomOptions = resources.getStringArray(R.array.room_options).toList()
        val floorOptions = resources.getStringArray(R.array.floor_options).toList()
        val centreOptions = resources.getStringArray(R.array.centre_options).toList()
        val ownershipOptions = resources.getStringArray(R.array.ownership_options).toList()
        val priceOptions = resources.getStringArray(R.array.price_options).toList()

        setUpDropdown(binding.cityFilter.editText, cityOptions)
        setUpDropdown(binding.typeFilter.editText, typeOptions)
        setUpDropdown(binding.areaFilter.editText, areaOptions)
        setUpDropdown(binding.roomFilter.editText, roomOptions)
        setUpDropdown(binding.floorFilter.editText, floorOptions)
        setUpDropdown(binding.centreFilter.editText, centreOptions)
        setUpDropdown(binding.ownershipFilter.editText, ownershipOptions)
        setUpDropdown(binding.priceFilter.editText, priceOptions)
    }

    private fun setUpDropdown(autoCompleteTextView: View?, options: List<String>) {
        if (autoCompleteTextView is android.widget.AutoCompleteTextView) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options)
            autoCompleteTextView.setAdapter(adapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}