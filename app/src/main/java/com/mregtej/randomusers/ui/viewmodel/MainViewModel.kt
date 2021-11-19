package com.mregtej.randomusers.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mregtej.randomusers.architecture.LiveDataEvent
import com.mregtej.randomusers.architecture.send
import com.mregtej.randomusers.database.model.RandomUserDetailsData
import com.mregtej.randomusers.database.model.RandomUserListData
import com.mregtej.randomusers.repository.Repository
import com.mregtej.randomusers.ui.state.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _randomUsersState = MutableLiveData<Resource<List<RandomUserListData>>>()
    private val _detailedUserState = MutableLiveData<LiveDataEvent<Resource<RandomUserDetailsData>>>()

    val randomUsersState: LiveData<Resource<List<RandomUserListData>>> = _randomUsersState
    val detailedUserState: LiveData<LiveDataEvent<Resource<RandomUserDetailsData>>> = _detailedUserState

    init {
        getRandomUsers()
    }

    private fun getRandomUsers() = viewModelScope.launch {
        _randomUsersState.value = Resource.loading(null)
        val result = try {
            Resource.success(repository.getUsers())
        } catch (e: Exception) {
            Resource.error(null, e.toString())
        }
        _randomUsersState.value = result
    }

    fun addNewRandomUsers() = viewModelScope.launch {
        _randomUsersState.value = Resource.loading(null)
        val result = try {
            Resource.success(repository.addNewUsers())
        } catch (e: Exception) {
            Resource.error(null, e.toString())
        }
        _randomUsersState.value = result
    }

    fun onUserClicked(uuid: String) = viewModelScope.launch {
        _detailedUserState.send(Resource.loading(null))
        val result = try {
            Resource.success(repository.getDetailedUser(uuid))
        } catch (e: Exception) {
            Resource.error(null, e.toString())
        }
        _detailedUserState.send(result)
    }
}