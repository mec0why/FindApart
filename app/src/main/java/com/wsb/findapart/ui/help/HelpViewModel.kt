package com.wsb.findapart.ui.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HelpViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to the FindApart app! This guide will help you navigate through the features and efficiently search for properties.\n" +
                "\n" +
                "On the Home (Search) screen, you can use various filters to find properties based on city, type (e.g., block of flats, tenement), square meters, number of rooms, floor, centre distance, ownership type, and price. Tap Search to view results or Clear to reset the filters.\n" +
                "\n" +
                "The List (Search Results) screen displays a list of properties matching your criteria. Each listing includes details such as location, type, price, size, number of rooms, and floor. Tapping a property provides more details. You can also use the Sort By button to organize listings by price, square meters, number of rooms, floor, or centre distance in ascending or descending order, or sort randomly.\n" +
                "\n" +
                "The Map screen allows you to view properties on a map, making location-based searching easier. Tap on a property marker to see the price and key details. The blue markers indicate available properties.\n" +
                "\n" +
                "On the Saved screen, you can view properties you have saved for future reference. To save a property, tap the star icon on any listing. Saved properties include full details for easy access later.\n" +
                "\n" +
                "You can navigate the app using the bottom menu, which allows you to switch between Home (Search), List (Results), Map (Location view), and Saved (Favorites).\n" +
                "\n" +
                "For further assistance, contact support. Happy house hunting!"
    }
    val text: LiveData<String> = _text
}