package com.wsb.findapart.ui.home

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wsb.findapart.R
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

        binding.searchButton.setOnClickListener {
            performSearch()
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
                    db.execSQL(sqlScript)
                    db.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    private fun performSearch() {
        val city = binding.cityFilter.editText?.text.toString()
        val type = binding.typeFilter.editText?.text.toString()
        val area = binding.areaFilter.editText?.text.toString()
        val room = binding.roomFilter.editText?.text.toString()
        val floor = binding.floorFilter.editText?.text.toString()
        val centre = binding.centreFilter.editText?.text.toString()
        val ownership = binding.ownershipFilter.editText?.text.toString()
        val price = binding.priceFilter.editText?.text.toString()

        val query = buildQuery(city, type, area, room, floor, centre, ownership, price)

        val cursor = db.rawQuery(query, null)
        if (cursor != null && cursor.count > 0) {
            Toast.makeText(requireContext(), "Found ${cursor.count} results", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT).show()
        }

        cursor.close()
    }

    private fun buildQuery(
        city: String, type: String, area: String, room: String, floor: String,
        centre: String, ownership: String, price: String
    ): String {
        val baseQuery = "SELECT * FROM apartments WHERE 1=1"
        val conditions = mutableListOf<String>()

        if (city.isNotEmpty()) conditions.add("city = '$city'")
        if (type.isNotEmpty()) conditions.add("type = '$type'")
        if (area.isNotEmpty()) conditions.add("area = '$area'")
        if (room.isNotEmpty()) conditions.add("room_count = '$room'")
        if (floor.isNotEmpty()) conditions.add("floor = '$floor'")
        if (centre.isNotEmpty()) conditions.add("centre = '$centre'")
        if (ownership.isNotEmpty()) conditions.add("ownership = '$ownership'")
        if (price.isNotEmpty()) conditions.add("price <= '$price'")

        return if (conditions.isNotEmpty()) {
            "$baseQuery AND ${conditions.joinToString(" AND ")}"
        } else {
            baseQuery
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}