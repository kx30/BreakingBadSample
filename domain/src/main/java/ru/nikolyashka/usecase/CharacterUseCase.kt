package ru.nikolyashka.usecase

import ru.nikolyashka.domain.CharacterType

interface CharacterUseCase {

    fun areThereMoreCharacters(): Boolean
    suspend fun getInitialCharacters(): List<CharacterType>
    suspend fun getCharacters(): List<CharacterType>
    suspend fun getCharactersBySearch(searchingText: String): List<CharacterType>
}