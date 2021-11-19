package com.mregtej.randomusers.repository

import com.mregtej.randomusers.database.dao.RandomUserDao
import com.mregtej.randomusers.helper.ParserTestHelper.expectedEmptyUsersFromJson
import com.mregtej.randomusers.helper.ParserTestHelper.expectedUsersFromJson
import com.mregtej.randomusers.mapper.RandomUserMapper
import com.mregtej.randomusers.network.model.ResponseBody
import com.mregtej.randomusers.network.service.Service
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MainRepositoryNetworkTest {

    @Mock
    lateinit var service: Service

    @Mock
    lateinit var dao: RandomUserDao

    @Mock
    lateinit var mapper: RandomUserMapper

    private lateinit var repository: RepositoryImpl

    @Before
    fun setUp() = setUpRepository()

    @Test
    fun should_retrieve_users_from_network() = runBlocking<Unit> {
        // Arrange
        setUpSuccessUsers(expectedUsersFromJson)

        // Act
        val users = repository.getUsersFromNetwork()

        // Assert
        assertThat(users).isEqualTo(expectedUsersFromJson)
    }

    @Test
    fun should_retrieve_empty_users_from_network() = runBlocking<Unit> {
        // Arrange
        setUpSuccessUsers(expectedEmptyUsersFromJson)

        // Act
        val users = repository.getUsersFromNetwork()

        // Assert
        assertThat(users).isEqualTo(expectedEmptyUsersFromJson)
    }

    private fun setUpSuccessUsers(response: ResponseBody) = runBlocking {
        whenever(service.getRandomUsers(any())) doReturn response
    }

    private fun setUpRepository() {
        repository = RepositoryImpl(service, dao, mapper)
    }
}