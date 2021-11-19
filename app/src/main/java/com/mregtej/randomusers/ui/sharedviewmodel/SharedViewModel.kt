package com.mregtej.randomusers.ui.sharedviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mregtej.randomusers.architecture.LiveDataEvent
import com.mregtej.randomusers.architecture.send
import com.mregtej.randomusers.database.model.RandomUserDetailsData

class SharedViewModel : ViewModel() {

    private val _navigateToUserDetails = MutableLiveData<LiveDataEvent<RandomUserDetailsData>>()
    val navigateToUserDetails: LiveData<LiveDataEvent<RandomUserDetailsData>> = _navigateToUserDetails

    fun onNavigateToUserDetails(user: RandomUserDetailsData) {
        _navigateToUserDetails.send(user)
    }
}