package com.wsb.findapart.ui.map

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.wsb.findapart.R
import java.text.NumberFormat
import java.util.Locale

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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 5.5f))
        googleMap.setMapStyle(context?.let { MapStyleOptions.loadRawResourceStyle(it, R.raw.map_style) })
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isCompassEnabled = true

        val apartments = getAllApartments()
        val numberFormat = NumberFormat.getInstance(Locale.US)
        apartments.forEach { (latitude, longitude, price) ->
            val formattedPrice = numberFormat.format(price)
            val markerOptions = MarkerOptions()
                .position(LatLng(latitude, longitude))
                .title("Saved Apartment")
                .snippet("$formattedPrice PLN")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))

            googleMap.addMarker(markerOptions)
        }

        arguments?.let { bundle ->
            val latitude = bundle.getDouble("latitude")
            val longitude = bundle.getDouble("longitude")
            val price = bundle.getInt("price")
            val formattedPrice = numberFormat.format(price)
            val location = LatLng(latitude, longitude)

            googleMap.addMarker(MarkerOptions()
                .position(location)
                .title("Selected Apartment")
                .snippet("$formattedPrice PLN")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }
    }

    private fun getAllApartments(): List<Triple<Double, Double, Int>> {
        val db = SQLiteDatabase.openDatabase(
            requireContext().getDatabasePath("apartments.db").path,
            null,
            SQLiteDatabase.OPEN_READONLY
        )

        val apartments = mutableListOf<Triple<Double, Double, Int>>()
        val query = "SELECT latitude, longitude, price FROM saved_apartments"

        db.rawQuery(query, null).use { cursor ->
            if (cursor.moveToFirst()) {
                val latitudeIndex = cursor.getColumnIndex("latitude")
                val longitudeIndex = cursor.getColumnIndex("longitude")
                val priceIndex = cursor.getColumnIndex("price")

                do {
                    val latitude = if (latitudeIndex != -1) cursor.getDouble(latitudeIndex) else 0.0
                    val longitude = if (longitudeIndex != -1) cursor.getDouble(longitudeIndex) else 0.0
                    val price = if (priceIndex != -1) cursor.getInt(priceIndex) else 0

                    apartments.add(Triple(latitude, longitude, price))
                } while (cursor.moveToNext())
            }
        }
        db.close()

        return apartments
    }
}