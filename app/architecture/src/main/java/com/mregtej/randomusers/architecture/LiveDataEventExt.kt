package com.mregtej.randomusers.architecture

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<LiveDataEvent<T>>.send(content: T) {
    value = LiveDataEvent(content)
}

fun <T> MutableLiveData<LiveDataEvent<T>>.post(content: T) {
    postValue(LiveDataEvent(content))
}