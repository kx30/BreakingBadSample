package ru.nikolyashka.breakingbadsample.di.gateways

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nikolyashka.breakingbadsample.di.network.ApiModule
import ru.nikolyashka.gateway.gateways.RetrofitCharacterGateway
import ru.nikolyashka.gateways.CharacterGateway

@Module(includes = [ApiModule::class])
@InstallIn(SingletonComponent::class)
abstract class GatewayModule {

    @Binds
    abstract fun bindCharacterGateway(retrofitCharacterGateway: RetrofitCharacterGateway): CharacterGateway
}