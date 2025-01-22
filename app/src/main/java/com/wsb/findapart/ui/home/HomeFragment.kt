package com.wsb.findapart.ui.home

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wsb.findapart.R
import com.wsb.findapart.data.DropdownMapping.centreDistanceMapping
import com.wsb.findapart.data.DropdownMapping.cityMapping
import com.wsb.findapart.data.DropdownMapping.floorMapping
import com.wsb.findapart.data.DropdownMapping.ownershipMapping
import com.wsb.findapart.data.DropdownMapping.priceMapping
import com.wsb.findapart.data.DropdownMapping.roomsMapping
import com.wsb.findapart.data.DropdownMapping.squareMetersMapping
import com.wsb.findapart.data.DropdownMapping.typeMapping
import com.wsb.findapart.databinding.FragmentHomeBinding
import java.io.IOException

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: SQLiteDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        db = importDatabase()

        val dropdownData = mapOf(
            binding.cityFilter.editText to R.array.city_options,
            binding.typeFilter.editText to R.array.type_options,
            binding.areaFilter.editText to R.array.area_options,
            binding.roomFilter.editText to R.array.room_options,
            binding.floorFilter.editText to R.array.floor_options,
            binding.centreFilter.editText to R.array.centre_options,
            binding.ownershipFilter.editText to R.array.ownership_options,
            binding.priceFilter.editText to R.array.price_options
        )

        dropdownData.forEach { (view, arrayId) ->
            setUpDropdown(view, resources.getStringArray(arrayId).toList())
        }

        binding.searchButton.setOnClickListener {
            performSearch()
        }

        binding.clearButton.setOnClickListener {
            clearFilters()
        }
    }

    private fun setUpDropdown(autoCompleteTextView: View?, options: List<String>) {
        if (autoCompleteTextView is AutoCompleteTextView) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options)
            autoCompleteTextView.setAdapter(adapter)
        }
    }

    private fun importDatabase(): SQLiteDatabase {
        val dbFile = requireContext().getDatabasePath("apartments.db")
        if (!dbFile.exists()) {
            try {
                requireContext().assets.open("apartments.sql").use { input ->
                    val sqlScript = input.bufferedReader().use { it.readText() }
                    val db = requireContext().openOrCreateDatabase("apartments.db", android.content.Context.MODE_PRIVATE, null)
                    db.beginTransaction()
                    sqlScript.split(";").filter { it.isNotBlank() }.forEach { statement ->
                        db.execSQL(statement)
                    }
                    db.setTransactionSuccessful()
                    db.endTransaction()
                    db.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    private fun performSearch() {
        val filters = listOf(
            "city" to Pair(binding.cityFilter.editText?.text.toString(), cityMapping),
            "type" to Pair(binding.typeFilter.editText?.text.toString(), typeMapping),
            "squareMeters" to Pair(binding.areaFilter.editText?.text.toString(), squareMetersMapping),
            "rooms" to Pair(binding.roomFilter.editText?.text.toString(), roomsMapping),
            "floor" to Pair(binding.floorFilter.editText?.text.toString(), floorMapping),
            "centreDistance" to Pair(binding.centreFilter.editText?.text.toString(), centreDistanceMapping),
            "ownership" to Pair(binding.ownershipFilter.editText?.text.toString(), ownershipMapping),
            "price" to Pair(binding.priceFilter.editText?.text.toString(), priceMapping)
        )

        val conditions = mutableListOf<String>()

        filters.forEach { (key, value) ->
            val filterValue = value.first
            val mapping = value.second

            if (filterValue.isNotEmpty()) {
                when (key) {
                    "squareMeters", "rooms", "floor", "centreDistance", "price" -> {
                        mapping[filterValue]?.let { condition ->
                            conditions.add(condition)
                        }
                    }
                    else -> {
                        mapping[filterValue]?.let { mappedValue ->
                            conditions.add("$key = '$mappedValue'")
                        }
                    }
                }
            }
        }

        val query = if (conditions.isNotEmpty()) {
            "SELECT COUNT(*) FROM apartments WHERE ${conditions.joinToString(" AND ")}"
        } else {
            "SELECT COUNT(*) FROM apartments"
        }

        val count = db.rawQuery(query, null).use { cursor ->
            if (cursor.moveToFirst()) cursor.getInt(0) else 0
        }

        if (count > 0) {
            val bundle = Bundle().apply {
                filters.forEach { (key, value) ->
                    putString(key, value.second[value.first] ?: "")
                }
            }
            Toast.makeText(requireContext(), "Found $count results", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_homeFragment_to_listFragment, bundle)
        } else {
            Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearFilters() {
        binding.cityFilter.editText?.setText("")
        binding.typeFilter.editText?.setText("")
        binding.areaFilter.editText?.setText("")
        binding.roomFilter.editText?.setText("")
        binding.floorFilter.editText?.setText("")
        binding.centreFilter.editText?.setText("")
        binding.ownershipFilter.editText?.setText("")
        binding.priceFilter.editText?.setText("")

        Toast.makeText(requireContext(), "Filters cleared", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::db.isInitialized && db.isOpen) {
            db.close()
        }
        _binding = null
    }
}