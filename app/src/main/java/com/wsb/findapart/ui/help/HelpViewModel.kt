package com.wsb.findapart.ui.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HelpViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "<p>Welcome to the <b>FindApart</b> app! This guide will help you navigate through the features and efficiently search for properties.</p>" +
                "<p>On the <b>Home (Search)</b> screen, you can use various filters to find properties based on <b>city, type, square meters, number of rooms, floor, centre distance, ownership type, and price</b>. Tap <b>Search</b> to view results or <b>Clear</b> to reset the filters.</p>" +
                "<p>The <b>List (Results)</b> screen displays a list of properties matching your criteria. Each listing includes details such as <b>location, type, price, size, number of rooms, and floor</b>. Tapping a property provides more details. You can also use the <b>Sort By</b> button to organize listings by <b>price, square meters, number of rooms, floor, or centre distance</b> in ascending or descending order, or sort randomly.</p>" +
                "<p>The <b>Map</b> screen allows you to view properties on a map, making <b>location-based searching</b> easier. Tap on a property marker to see the <b>price and key details</b>. The <b>blue markers</b> indicate available properties.</p>" +
                "<p>On the <b>Saved</b> screen, you can view properties you have saved for future reference. To save a property, tap the <b>star icon</b> on any listing. Saved properties include <b>full details</b> for easy access later.</p>" +
                "<p>You can navigate the app using the <b>bottom menu</b>, which allows you to switch between <b>Home (Search), List (Results), Map (Location), and Saved (Favorites)</b>.</p>" +
                "<p>For further assistance, contact support.<br><b>Happy house hunting!</b></p>"
    }
    val text: LiveData<String> = _text
}