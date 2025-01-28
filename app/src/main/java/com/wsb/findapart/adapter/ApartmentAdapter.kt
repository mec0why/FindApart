package com.wsb.findapart.adapter

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.wsb.findapart.R
import com.wsb.findapart.data.ListMapping
import com.wsb.findapart.databinding.ItemApartmentBinding
import com.wsb.findapart.model.Apartment
import java.util.Locale

class ApartmentAdapter(private val apartments: MutableList<Apartment>) :
    RecyclerView.Adapter<ApartmentAdapter.ApartmentViewHolder>() {

    inner class ApartmentViewHolder(private val binding: ItemApartmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(apartment: Apartment) {
            binding.tvCity.text = "City: ${ListMapping.cityMapping[apartment.city] ?: apartment.city}"
            binding.tvType.text = "Type: ${ListMapping.typeMapping[apartment.type] ?: apartment.type}"
            binding.ivTypeIcon.setImageResource(getTypeIcon(apartment.type))

            binding.tvArea.text = String.format(Locale.getDefault(), "Square Meters: %.2f m", apartment.squareMeters)
            binding.tvRoom.text = String.format(Locale.getDefault(), "Rooms: %d", apartment.rooms)
            binding.tvFloor.text = String.format(Locale.getDefault(), "Floor: %d", apartment.floor)
            binding.tvFloorCount.text = String.format(Locale.getDefault(), "Floor Count: %d", apartment.floorCount)
            binding.tvBuildYear.text = String.format(Locale.getDefault(), "Build Year: %s", if (apartment.buildYear == 0) "-" else apartment.buildYear.toString())
            binding.tvCentreDistance.text = String.format(Locale.getDefault(), "Centre Distance: %.0f m", apartment.centreDistance * 1000)
            binding.tvPoiCount.text = String.format(Locale.getDefault(), "POI Count: %d", apartment.poiCount)
            binding.tvSchoolDistance.text = String.format(Locale.getDefault(), "School Distance: %.0f m", apartment.schoolDistance * 1000)
            binding.tvClinicDistance.text = String.format(Locale.getDefault(), "Clinic Distance: %.0f m", apartment.clinicDistance * 1000)
            binding.tvPostOfficeDistance.text = String.format(Locale.getDefault(), "Post Office Distance: %.0f m", apartment.postOfficeDistance * 1000)
            binding.tvKindergartenDistance.text = String.format(Locale.getDefault(), "Kindergarten Distance: %.0f m", apartment.kindergartenDistance * 1000)
            binding.tvRestaurantDistance.text = String.format(Locale.getDefault(), "Restaurant Distance: %.0f m", apartment.restaurantDistance * 1000)
            binding.tvCollegeDistance.text = String.format(Locale.getDefault(), "College Distance: %.0f m", apartment.collegeDistance * 1000)
            binding.tvPharmacyDistance.text = String.format(Locale.getDefault(), "Pharmacy Distance: %.0f m", apartment.pharmacyDistance * 1000)

            binding.tvOwnership.text = "Ownership: ${ListMapping.ownershipMapping[apartment.ownership] ?: apartment.ownership}"
            binding.tvBuildingMaterial.text = "Building Material: ${ListMapping.buildingMaterialMapping[apartment.buildingMaterial] ?: apartment.buildingMaterial}"
            binding.tvCondition.text = "Condition: ${ListMapping.conditionMapping[apartment.condition] ?: apartment.condition}"
            binding.tvHasParkingSpace.text = "Parking Space: ${apartment.hasParkingSpace}"
            binding.tvHasBalcony.text = "Balcony: ${apartment.hasBalcony}"
            binding.tvHasElevator.text = "Elevator: ${apartment.hasElevator}"
            binding.tvHasSecurity.text = "Security: ${apartment.hasSecurity}"
            binding.tvHasStorageRoom.text = "Storage Room: ${apartment.hasStorageRoom}"

            binding.tvPrice.text = String.format(Locale.getDefault(), "Price: %,d PLN", apartment.price)

            binding.detailsContainer.visibility = if (apartment.isDetailsVisible) View.VISIBLE else View.GONE
            binding.buttonsContainer.visibility = if (apartment.isDetailsVisible) View.VISIBLE else View.GONE

            binding.root.setOnClickListener {
                toggleDetailsVisibility(apartment)
            }

            binding.btnSave.setOnCheckedChangeListener(null)
            binding.btnSave.isChecked = apartment.isSaved

            binding.btnSave.setOnCheckedChangeListener { _, isChecked ->
                apartment.isSaved = isChecked
                if (isChecked) {
                    saveApartment(apartment)
                } else {
                    removeApartment(apartment)
                }
            }

            binding.btnMap.setOnClickListener {
                val latitude = apartment.latitude
                val longitude = apartment.longitude
                val price = apartment.price

                val bundle = Bundle().apply {
                    putDouble("latitude", latitude)
                    putDouble("longitude", longitude)
                    putInt("price", price)
                }

                Navigation.findNavController(binding.root).navigate(R.id.action_listFragment_to_mapFragment, bundle)
            }

            checkIfApartmentSaved(apartment)
        }

        private fun checkIfApartmentSaved(apartment: Apartment) {
            val db = SQLiteDatabase.openDatabase(
                itemView.context.getDatabasePath("apartments.db").path,
                null,
                SQLiteDatabase.OPEN_READWRITE
            )

            val query = "SELECT COUNT(*) FROM saved_apartments WHERE id = ?"
            val cursor = db.rawQuery(query, arrayOf(apartment.id))

            cursor.use {
                if (it.moveToFirst()) {
                    val count = it.getInt(0)
                    apartment.isSaved = (count > 0)
                }
            }

            db.close()
        }

        private fun toggleDetailsVisibility(apartment: Apartment) {
            apartment.isDetailsVisible = !apartment.isDetailsVisible
            notifyItemChanged(adapterPosition)
        }

        private fun getTypeIcon(type: String): Int {
            return when (type) {
                "apartmentBuilding" -> R.drawable.apartment_48px
                "blockOfFlats" -> R.drawable.domain_48px
                "tenement" -> R.drawable.location_city_48px
                else -> 0
            }
        }

        private fun saveApartment(apartment: Apartment) {
            val db = SQLiteDatabase.openDatabase(
                itemView.context.getDatabasePath("apartments.db").path,
                null,
                SQLiteDatabase.OPEN_READWRITE
            )

            val query = """
                INSERT OR REPLACE INTO saved_apartments (
                    id, city, type, squareMeters, rooms, floor, floorCount, buildYear,
                    centreDistance, poiCount, schoolDistance, clinicDistance, postOfficeDistance,
                    kindergartenDistance, restaurantDistance, collegeDistance, pharmacyDistance,
                    ownership, buildingMaterial, condition, hasParkingSpace, hasBalcony,
                    hasElevator, hasSecurity, hasStorageRoom, price, latitude, longitude
                ) VALUES (
                    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
                );
            """.trimIndent()

            db.execSQL(
                query, arrayOf(
                    apartment.id,
                    apartment.city,
                    apartment.type,
                    apartment.squareMeters,
                    apartment.rooms,
                    apartment.floor,
                    apartment.floorCount,
                    apartment.buildYear,
                    apartment.centreDistance,
                    apartment.poiCount,
                    apartment.schoolDistance,
                    apartment.clinicDistance,
                    apartment.postOfficeDistance,
                    apartment.kindergartenDistance,
                    apartment.restaurantDistance,
                    apartment.collegeDistance,
                    apartment.pharmacyDistance,
                    apartment.ownership,
                    apartment.buildingMaterial,
                    apartment.condition,
                    apartment.hasParkingSpace,
                    apartment.hasBalcony,
                    apartment.hasElevator,
                    apartment.hasSecurity,
                    apartment.hasStorageRoom,
                    apartment.price,
                    apartment.latitude,
                    apartment.longitude
                )
            )

            Toast.makeText(
                itemView.context,
                "Apartment added to saved list",
                Toast.LENGTH_SHORT
            ).show()

            db.close()
        }

        private fun removeApartment(apartment: Apartment) {
            val db = SQLiteDatabase.openDatabase(
                itemView.context.getDatabasePath("apartments.db").path,
                null,
                SQLiteDatabase.OPEN_READWRITE
            )

            val query = "DELETE FROM saved_apartments WHERE id = ?"
            db.execSQL(query, arrayOf(apartment.id))
            db.close()

            Toast.makeText(
                itemView.context,
                "Apartment removed from saved list",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApartmentViewHolder {
        val binding = ItemApartmentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ApartmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        holder.bind(apartments[position])
    }

    override fun getItemCount(): Int = apartments.size
}