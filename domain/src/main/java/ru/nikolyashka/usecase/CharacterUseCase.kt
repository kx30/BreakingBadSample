package ru.nikolyashka.usecase

import ru.nikolyashka.domain.CharacterType

interface CharacterUseCase {

    fun getInitialData(): List<CharacterType>

    suspend fun getCharacters(): List<CharacterType>
}