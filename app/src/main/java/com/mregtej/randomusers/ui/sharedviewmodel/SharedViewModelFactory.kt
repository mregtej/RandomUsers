package com.mregtej.randomusers.ui.sharedviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory

class SharedViewModelFactory : Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = SharedViewModel() as T
}