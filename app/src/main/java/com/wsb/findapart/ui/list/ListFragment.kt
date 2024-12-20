package com.wsb.findapart.ui.list

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wsb.findapart.R
import com.wsb.findapart.adapter.ApartmentAdapter
import com.wsb.findapart.databinding.FragmentListBinding
import com.wsb.findapart.model.Apartment

class ListFragment : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: SQLiteDatabase
    private lateinit var adapter: ApartmentAdapter
    private lateinit var apartments: MutableList<Apartment>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        db = SQLiteDatabase.openDatabase(
            requireContext().getDatabasePath("apartments.db").path,
            null,
            SQLiteDatabase.OPEN_READWRITE
        )

        apartments = mutableListOf()

        val cityArg = arguments?.getString("city") ?: ""
        val typeArg = arguments?.getString("type") ?: ""
        val areaArg = arguments?.getString("squareMeters") ?: ""
        val roomArg = arguments?.getString("rooms") ?: ""
        val floorArg = arguments?.getString("floor") ?: ""
        val centreArg = arguments?.getString("centreDistance") ?: ""
        val ownershipArg = arguments?.getString("ownership") ?: ""
        val priceArg = arguments?.getString("price") ?: ""

        val query = buildQuery(cityArg, typeArg, areaArg, roomArg, floorArg, centreArg, ownershipArg, priceArg)

        db.rawQuery(query, null).use { cursor ->
            if (cursor.count > 0 && cursor.moveToFirst()) {
                Toast.makeText(requireContext(), "Found ${cursor.count} results", Toast.LENGTH_SHORT).show()
                do {
                    val idIndex = cursor.getColumnIndex("id")
                    val cityIndex = cursor.getColumnIndex("city")
                    val typeIndex = cursor.getColumnIndex("type")
                    val squareMetersIndex = cursor.getColumnIndex("squareMeters")
                    val roomsIndex = cursor.getColumnIndex("rooms")
                    val floorIndex = cursor.getColumnIndex("floor")
                    val floorCountIndex = cursor.getColumnIndex("floorCount")
                    val buildYearIndex = cursor.getColumnIndex("buildYear")
                    val centreDistanceIndex = cursor.getColumnIndex("centreDistance")
                    val poiCountIndex = cursor.getColumnIndex("poiCount")
                    val schoolDistanceIndex = cursor.getColumnIndex("schoolDistance")
                    val clinicDistanceIndex = cursor.getColumnIndex("clinicDistance")
                    val postOfficeDistanceIndex = cursor.getColumnIndex("postOfficeDistance")
                    val kindergartenDistanceIndex = cursor.getColumnIndex("kindergartenDistance")
                    val restaurantDistanceIndex = cursor.getColumnIndex("restaurantDistance")
                    val collegeDistanceIndex = cursor.getColumnIndex("collegeDistance")
                    val pharmacyDistanceIndex = cursor.getColumnIndex("pharmacyDistance")
                    val ownershipIndex = cursor.getColumnIndex("ownership")
                    val buildingMaterialIndex = cursor.getColumnIndex("buildingMaterial")
                    val conditionIndex = cursor.getColumnIndex("condition")
                    val hasParkingSpaceIndex = cursor.getColumnIndex("hasParkingSpace")
                    val hasBalconyIndex = cursor.getColumnIndex("hasBalcony")
                    val hasElevatorIndex = cursor.getColumnIndex("hasElevator")
                    val hasSecurityIndex = cursor.getColumnIndex("hasSecurity")
                    val hasStorageRoomIndex = cursor.getColumnIndex("hasStorageRoom")
                    val priceIndex = cursor.getColumnIndex("price")

                    val apartment = Apartment(
                        id = if (idIndex != -1) cursor.getString(idIndex) else "",
                        city = if (cityIndex != -1) cursor.getString(cityIndex) else "",
                        type = if (typeIndex != -1) cursor.getString(typeIndex) else "",
                        squareMeters = if (squareMetersIndex != -1) cursor.getDouble(squareMetersIndex) else 0.0,
                        rooms = if (roomsIndex != -1) cursor.getInt(roomsIndex) else 0,
                        floor = if (floorIndex != -1) cursor.getInt(floorIndex) else 0,
                        floorCount = if (floorCountIndex != -1) cursor.getInt(floorCountIndex) else 0,
                        buildYear = if (buildYearIndex != -1) cursor.getInt(buildYearIndex) else 0,
                        centreDistance = if (centreDistanceIndex != -1) cursor.getDouble(centreDistanceIndex) else 0.0,
                        poiCount = if (poiCountIndex != -1) cursor.getInt(poiCountIndex) else 0,
                        schoolDistance = if (schoolDistanceIndex != -1) cursor.getDouble(schoolDistanceIndex) else 0.0,
                        clinicDistance = if (clinicDistanceIndex != -1) cursor.getDouble(clinicDistanceIndex) else 0.0,
                        postOfficeDistance = if (postOfficeDistanceIndex != -1) cursor.getDouble(postOfficeDistanceIndex) else 0.0,
                        kindergartenDistance = if (kindergartenDistanceIndex != -1) cursor.getDouble(kindergartenDistanceIndex) else 0.0,
                        restaurantDistance = if (restaurantDistanceIndex != -1) cursor.getDouble(restaurantDistanceIndex) else 0.0,
                        collegeDistance = if (collegeDistanceIndex != -1) cursor.getDouble(collegeDistanceIndex) else 0.0,
                        pharmacyDistance = if (pharmacyDistanceIndex != -1) cursor.getDouble(pharmacyDistanceIndex) else 0.0,
                        ownership = if (ownershipIndex != -1) cursor.getString(ownershipIndex) else "",
                        buildingMaterial = if (buildingMaterialIndex != -1) cursor.getString(buildingMaterialIndex) ?: "" else "",
                        condition = if (conditionIndex != -1) cursor.getString(conditionIndex) ?: "" else "",
                        hasParkingSpace = if (hasParkingSpaceIndex != -1) cursor.getString(hasParkingSpaceIndex) else "",
                        hasBalcony = if (hasBalconyIndex != -1) cursor.getString(hasBalconyIndex) else "",
                        hasElevator = if (hasElevatorIndex != -1) cursor.getString(hasElevatorIndex) ?: "" else "",
                        hasSecurity = if (hasSecurityIndex != -1) cursor.getString(hasSecurityIndex) else "",
                        hasStorageRoom = if (hasStorageRoomIndex != -1) cursor.getString(hasStorageRoomIndex) else "",
                        price = if (priceIndex != -1) cursor.getInt(priceIndex) else 0
                    )

                    apartments.add(apartment)
                } while (cursor.moveToNext())
            } else {
                Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT).show()
            }
        }

        adapter = ApartmentAdapter(apartments)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun buildQuery(
        city: String, type: String, squareMeters: String, rooms: String, floor: String,
        centreDistance: String, ownership: String, price: String
    ): String {
        val baseQuery = "SELECT * FROM apartments WHERE 1=1"
        val conditions = mutableListOf<String>()

        if (city.isNotEmpty()) conditions.add("city = '$city'")
        if (type.isNotEmpty()) conditions.add("type = '$type'")
        if (squareMeters.isNotEmpty()) conditions.add(squareMeters)
        if (rooms.isNotEmpty()) conditions.add(rooms)
        if (floor.isNotEmpty()) conditions.add(floor)
        if (centreDistance.isNotEmpty()) conditions.add(centreDistance)
        if (ownership.isNotEmpty()) conditions.add("ownership = '$ownership'")
        if (price.isNotEmpty()) conditions.add(price)

        return if (conditions.isNotEmpty()) {
            "$baseQuery AND ${conditions.joinToString(" AND ")}"
        } else {
            baseQuery
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::db.isInitialized && db.isOpen) {
            db.close()
        }
        _binding = null
    }
}