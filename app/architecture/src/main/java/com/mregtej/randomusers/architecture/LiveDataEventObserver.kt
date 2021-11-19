package com.mregtej.randomusers.architecture

import androidx.lifecycle.Observer

class LiveDataEventObserver<T>(
    private val recipient: (T) -> Unit) : Observer<LiveDataEvent<T>?> {

    override fun onChanged(liveDataEvent: LiveDataEvent<T>?) {
        liveDataEvent?.getContentOnce()?.let(recipient)
    }
}