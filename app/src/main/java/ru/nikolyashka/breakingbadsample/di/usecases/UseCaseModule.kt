package ru.nikolyashka.breakingbadsample.di.usecases

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nikolyashka.usecase.*

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindCharactersUseCase(characterUseCase: CharacterUseCaseImpl): CharacterUseCase

    @Binds
    abstract fun bindFavoritesUseCase(favoritesUseCase: FavoritesUseCaseImpl): FavoritesUseCase

    @Binds
    abstract fun bindSearchUseCase(searchUseCase: SearchUseCaseImpl): SearchUseCase
}