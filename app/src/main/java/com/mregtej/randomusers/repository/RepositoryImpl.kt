package com.mregtej.randomusers.repository

import androidx.annotation.VisibleForTesting
import com.mregtej.randomusers.database.dao.RandomUserDao
import com.mregtej.randomusers.database.model.RandomUser
import com.mregtej.randomusers.database.model.RandomUserListData
import com.mregtej.randomusers.mapper.RandomUserMapper
import com.mregtej.randomusers.network.service.Service
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class RepositoryImpl(
    private val service: Service,
    private val dao: RandomUserDao,
    private val mapper: RandomUserMapper
): Repository {

    override suspend fun getUsers(): List<RandomUserListData> {
        handleFetchingNewUsers(!areThereUsersOnDB())
        return getUsersFromDB()
    }

    override suspend fun addNewUsers(): List<RandomUserListData> {
        handleFetchingNewUsers(true)
        return getUsersFromDB()
    }

    override suspend fun getDetailedUser(uuid: String) = withContext(IO) {
        dao.getDetailedUserById(uuid)
    }

    private suspend fun areThereUsersOnDB() = withContext(IO) {
        dao.getUserNumber() > 0
    }

    private suspend fun handleFetchingNewUsers(shallFetchNewUsers: Boolean) {
        if (shallFetchNewUsers) {
            fetchAndInsertUsers()
        }
    }

    private suspend fun fetchAndInsertUsers() =
        getUsersFromNetwork().results?.distinctBy { it.login.uuid }?.map {
            mapper.mapToRandomUserEntity(it).also { user ->
                insertUserIntoDB(user)
            }
        }

    @VisibleForTesting
    suspend fun getUsersFromNetwork() = service.getRandomUsers(MAX_NUMBER_OF_RESULTS)

    private suspend fun insertUserIntoDB(user: RandomUser) = withContext(IO) {
        dao.insertUser(user)
    }

    private suspend fun getUsersFromDB() = withContext(IO) {
        dao.getUsers()
    }

    private companion object Constants {
        private const val MAX_NUMBER_OF_RESULTS = 40
    }
}