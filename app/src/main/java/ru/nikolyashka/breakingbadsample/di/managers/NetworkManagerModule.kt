package ru.nikolyashka.breakingbadsample.di.managers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nikolyashka.breakingbadsample.core.NetworkManagerImpl
import ru.nikolyashka.core.NetworkManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkManagerModule {

    @Provides
    @Singleton
    fun provideNetworkManager(): NetworkManager = NetworkManagerImpl()
}