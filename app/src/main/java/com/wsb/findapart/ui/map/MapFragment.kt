package com.wsb.findapart.ui.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.wsb.findapart.R

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val initialLocation = LatLng(51.9189, 19.1344)
        googleMap.addMarker(MarkerOptions().position(initialLocation).title("Poland"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 6f))
        googleMap.setMapStyle(context?.let { MapStyleOptions.loadRawResourceStyle(it, R.raw.map_style) })
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
    }
}