package com.wsb.findapart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wsb.findapart.databinding.ItemApartmentBinding
import com.wsb.findapart.model.Apartment
import java.util.Locale

class ApartmentAdapter(private val apartments: List<Apartment>) :
    RecyclerView.Adapter<ApartmentAdapter.ApartmentViewHolder>() {

    inner class ApartmentViewHolder(private val binding: ItemApartmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(apartment: Apartment) {
            binding.tvCity.text = apartment.city
            binding.tvType.text = apartment.type

            binding.tvArea.text = String.format(Locale.getDefault(), "%.2f", apartment.squareMeters)
            binding.tvRoom.text = String.format(Locale.getDefault(), "%d", apartment.rooms)
            binding.tvFloor.text = String.format(Locale.getDefault(), "%d", apartment.floor)
            binding.tvFloorCount.text = String.format(Locale.getDefault(), "%d", apartment.floorCount)
            binding.tvBuildYear.text = String.format(Locale.getDefault(), "%d", apartment.buildYear)
            binding.tvCentreDistance.text = String.format(Locale.getDefault(), "%.2f", apartment.centreDistance)
            binding.tvPoiCount.text = String.format(Locale.getDefault(), "%d", apartment.poiCount)
            binding.tvSchoolDistance.text = String.format(Locale.getDefault(), "%.2f", apartment.schoolDistance)
            binding.tvClinicDistance.text = String.format(Locale.getDefault(), "%.2f", apartment.clinicDistance)
            binding.tvPostOfficeDistance.text = String.format(Locale.getDefault(), "%.2f", apartment.postOfficeDistance)
            binding.tvKindergartenDistance.text = String.format(Locale.getDefault(), "%.2f", apartment.kindergartenDistance)
            binding.tvRestaurantDistance.text = String.format(Locale.getDefault(), "%.2f", apartment.restaurantDistance)
            binding.tvCollegeDistance.text = String.format(Locale.getDefault(), "%.2f", apartment.collegeDistance)
            binding.tvPharmacyDistance.text = String.format(Locale.getDefault(), "%.2f", apartment.pharmacyDistance)

            binding.tvOwnership.text = apartment.ownership
            binding.tvBuildingMaterial.text = apartment.buildingMaterial
            binding.tvCondition.text = apartment.condition
            binding.tvHasParkingSpace.text = apartment.hasParkingSpace
            binding.tvHasBalcony.text = apartment.hasBalcony
            binding.tvHasElevator.text = apartment.hasElevator
            binding.tvHasSecurity.text = apartment.hasSecurity
            binding.tvHasStorageRoom.text = apartment.hasStorageRoom

            binding.tvPrice.text = String.format(Locale.getDefault(), "%,d", apartment.price)
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