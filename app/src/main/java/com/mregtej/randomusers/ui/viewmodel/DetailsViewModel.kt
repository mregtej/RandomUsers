package com.mregtej.randomusers.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mregtej.randomusers.database.model.RandomUserDetailsData

class DetailsViewModel(private val state: SavedStateHandle) : ViewModel() {

    val detailsData: LiveData<RandomUserDetailsData> =
        state.getLiveData(ARG_DETAILS_DATA)

    fun setData(data: RandomUserDetailsData) {
        state[ARG_DETAILS_DATA] = data
    }

    private companion object {
        private const val ARG_DETAILS_DATA = "ARG_DETAILS_DATA"
    }
}