package ru.nikolyashka.breakingbadsample.di.usecase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nikolyashka.usecase.CharacterUseCase
import ru.nikolyashka.usecase.CharacterUseCaseImpl
import ru.nikolyashka.usecase.FavoritesUseCase
import ru.nikolyashka.usecase.RoomFavoritesUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindCharactersUseCase(useCase: CharacterUseCaseImpl): CharacterUseCase

    @Binds
    abstract fun bindFavoritesUseCase(useCase: RoomFavoritesUseCase): FavoritesUseCase
}