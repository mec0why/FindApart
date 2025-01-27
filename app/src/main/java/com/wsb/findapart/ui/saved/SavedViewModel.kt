package com.wsb.findapart.ui.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "No saved apartments available"
    }
    val text: LiveData<String> = _text
}