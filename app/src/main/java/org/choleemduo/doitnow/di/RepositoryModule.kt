package org.choleemduo.doitnow.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.choleemduo.data.repositoryimpl.AuthenticationRepositoryImpl
import org.choleemduo.data.repositoryimpl.HomeRepositoryImpl
import org.choleemduo.domain.repository.AuthenticationRepository
import org.choleemduo.domain.repository.HomeRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindAuthenticationRepository(authRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository
}