package com.wsb.findapart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wsb.findapart.databinding.ItemListBinding
import com.wsb.findapart.model.ListItem

class ListAdapter(private val items: List<ListItem>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListItem) {
            binding.apply {
                tvCity.text = item.city
                tvType.text = item.type
                tvArea.text = item.area
                tvRoom.text = item.room
                tvFloor.text = item.floor
                tvFloorCount.text = item.floorcount
                tvBuildYear.text = item.buildyear
                tvCentreDistance.text = item.centredistance
                tvPoiCount.text = item.poicount
                tvSchoolDistance.text = item.schooldistance
                tvClinicDistance.text = item.clinicdistance
                tvPostOfficeDistance.text = item.postofficedistance
                tvKindergartenDistance.text = item.kindergartendistance
                tvRestaurantDistance.text = item.restaurantdistance
                tvCollegeDistance.text = item.collegedistance
                tvPharmacyDistance.text = item.pharmacydistance
                tvOwnership.text = item.ownership
                tvBuildingMaterial.text = item.buildingmaterial
                tvCondition.text = item.condition
                tvHasParkingSpace.text = item.hasparkingspace
                tvHasBalcony.text = item.hasbalcony
                tvHasElevator.text = item.haselevator
                tvHasSecurity.text = item.hassecurity
                tvHasStorageRoom.text = item.hasstorageroom
                tvPrice.text = item.price
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}