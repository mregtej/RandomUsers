package com.mregtej.randomusers.sharedviewmodel

import TestHelper.MODESTO_USER_DETAILED_DATA
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import com.mregtej.randomusers.architecture.LiveDataEvent
import com.mregtej.randomusers.ui.sharedviewmodel.SharedViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SharedViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel : SharedViewModel

    private fun setUpViewModel() {
        viewModel = SharedViewModel()
    }

    @Before
    fun setUp() = setUpViewModel()

    @Test
    fun should_navigate_to_user_details_when_requested() = runBlocking<Unit> {
        // Act
        viewModel.onNavigateToUserDetails(MODESTO_USER_DETAILED_DATA)

        // Assert
        viewModel.navigateToUserDetails.test().assertValue(LiveDataEvent(MODESTO_USER_DETAILED_DATA))
    }
}