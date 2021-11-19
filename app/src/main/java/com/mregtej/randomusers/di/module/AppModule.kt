package com.mregtej.randomusers.di.module

import com.mregtej.randomusers.database.dao.RandomUserDao
import com.mregtej.randomusers.mapper.RandomUserMapper
import com.mregtej.randomusers.network.service.Service
import com.mregtej.randomusers.repository.Repository
import com.mregtej.randomusers.repository.RepositoryImpl
import com.mregtej.randomusers.ui.AppFragmentFactory
import com.mregtej.randomusers.ui.sharedviewmodel.SharedViewModelFactory
import com.mregtej.randomusers.ui.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideAppFragmentFactory(mainViewModelFactory: MainViewModelFactory) =
        AppFragmentFactory(mainViewModelFactory)

    @Provides
    fun provideSharedViewModelFactory() = SharedViewModelFactory()

    @Provides
    fun provideMainViewModelFactory(repository: Repository) = MainViewModelFactory(repository)

    @Provides
    @Singleton
    fun provideMainRepository(
        service: Service,
        dao: RandomUserDao,
        mapper: RandomUserMapper
    ): Repository = RepositoryImpl(service, dao, mapper)

    @Provides
    @Singleton
    fun provideRandomUserMapper() = RandomUserMapper
}