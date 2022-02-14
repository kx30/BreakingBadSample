package ru.nikolyashka.usecase

import ru.nikolyashka.domain.CharacterType

interface SearchUseCase {

    suspend fun getCharactersBySearch(searchingText: String): List<CharacterType>
}