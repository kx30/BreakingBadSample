package ru.nikolyashka.usecase

import ru.nikolyashka.domain.CharacterType

interface CharacterUseCase {

    suspend fun getInitialData(): List<CharacterType>

    suspend fun getCharacters(): List<CharacterType>
}