package com.mregtej.randomusers.viewmodel

import TestHelper.EXCEPTION
import TestHelper.EXCEPTION_MSG
import TestHelper.MODESTO_USER_DETAILED_DATA
import TestHelper.MODESTO_UUID
import TestHelper.USERS
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import com.mregtej.randomusers.architecture.LiveDataEvent
import com.mregtej.randomusers.database.model.RandomUserDetailsData
import com.mregtej.randomusers.database.model.RandomUserListData
import com.mregtej.randomusers.helper.MainCoroutineDispatcherRule
import com.mregtej.randomusers.repository.Repository
import com.mregtej.randomusers.ui.state.Resource
import com.mregtej.randomusers.ui.viewmodel.MainViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcherRule = MainCoroutineDispatcherRule()

    @Mock
    private lateinit var repository: Repository

    private lateinit var viewModel : MainViewModel

    private fun setUpViewModel() {
        viewModel = MainViewModel(repository)
    }

    @Test
    fun should_retrieve_users_at_init() = runBlocking<Unit> {
        // Arrange
        setUpSuccessUsers()

        // Act
        setUpViewModel()

        // Assert
        viewModel.randomUsersState.test().assertValue(Resource.success(data = USERS))
    }

    @Test
    fun should_catch_an_exception_when_fetching_users_at_init_fails() = runBlocking<Unit> {
        // Arrange
        setUpErrorUsers()

        // Act
        setUpViewModel()

        // Assert
        viewModel.randomUsersState.test().assertValue(Resource.error(data = null, message = EXCEPTION))
    }

    @Test
    fun should_add_new_users_when_is_requested() = runBlocking<Unit> {
        // Arrange
        setUpSuccessUsers(emptyList())
        setUpSuccessAddUsers()
        setUpViewModel()
        val testObserver = viewModel.randomUsersState.test()

        // Act
        viewModel.addNewRandomUsers()

        // Assert
        testObserver.assertHistorySize(3).assertValueHistory(
            Resource.success(data = emptyList()),
            Resource.loading(data = null),
            Resource.success(data = USERS))
    }

    @Test
    fun should_catch_an_exception_when_adding_new_users_fails() = runBlocking<Unit> {
        // Arrange
        setUpSuccessUsers(emptyList())
        setUpErrorAddUsers()
        setUpViewModel()
        val testObserver = viewModel.randomUsersState.test()

        // Act
        viewModel.addNewRandomUsers()

        // Assert
        testObserver.assertHistorySize(3).assertValueHistory(
            Resource.success(data = emptyList()),
            Resource.loading(data = null),
            Resource.error(data = null, message = EXCEPTION))
    }

    @Test
    fun should_get_user_details_when_item_is_clicked() = runBlocking<Unit> {
        // Arrange
        setUpSuccessUsers()
        setUpSuccessDetailUser()
        setUpViewModel()
        val testObserver = viewModel.detailedUserState.test()

        // Act
        viewModel.onUserClicked(MODESTO_UUID)

        // Assert
        testObserver.assertHistorySize(2).assertValueHistory(
            LiveDataEvent(Resource.loading(data = null)),
            LiveDataEvent(Resource.success(data = MODESTO_USER_DETAILED_DATA))
        )
    }

    @Test
    fun should_catch_an_exception_when_fetching_user_details_fails() = runBlocking<Unit> {
        // Arrange
        setUpSuccessUsers()
        setUpErrorDetailUser()
        setUpViewModel()
        val testObserver = viewModel.detailedUserState.test()

        // Act
        viewModel.onUserClicked(MODESTO_UUID)

        // Assert
        testObserver.assertHistorySize(2).assertValueHistory(
            LiveDataEvent(Resource.loading(data = null)),
            LiveDataEvent(Resource.error(data = null, message = EXCEPTION))
        )
    }

    private fun setUpSuccessUsers(response: List<RandomUserListData> = USERS) = runBlocking {
        whenever(repository.getUsers()) doReturn response
    }

    private fun setUpErrorUsers(exception: Exception = Exception(EXCEPTION_MSG)) = runBlocking {
        whenever(repository.getUsers()) doThrow exception
    }

    private fun setUpSuccessAddUsers(response: List<RandomUserListData> = USERS) = runBlocking {
        whenever(repository.addNewUsers()) doReturn response
    }

    private fun setUpErrorAddUsers(exception: Exception = Exception(EXCEPTION_MSG)) = runBlocking {
        whenever(repository.addNewUsers()) doThrow exception
    }

    private fun setUpSuccessDetailUser(response: RandomUserDetailsData = MODESTO_USER_DETAILED_DATA) = runBlocking {
        whenever(repository.getDetailedUser(any())) doReturn response
    }

    private fun setUpErrorDetailUser(exception: Exception = Exception(EXCEPTION_MSG)) = runBlocking {
        whenever(repository.getDetailedUser(any())) doThrow exception
    }
}