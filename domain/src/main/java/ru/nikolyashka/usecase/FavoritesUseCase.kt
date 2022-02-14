package ru.nikolyashka.usecase

import ru.nikolyashka.domain.CharacterType

interface FavoritesUseCase {

    suspend fun getFavoriteCharacters(): List<CharacterType.CharacterModel>
    suspend fun changeFavoriteCharacterState(character: CharacterType.CharacterModel)
}