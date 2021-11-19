package com.mregtej.randomusers.helper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MainCoroutineDispatcherRule: TestWatcher() {

    @ExperimentalCoroutinesApi
    override fun starting(description: Description?) = Dispatchers.setMain(Dispatchers.Unconfined)

    @ExperimentalCoroutinesApi
    override fun finished(description: Description?) = Dispatchers.resetMain()
}