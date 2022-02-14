package ru.nikolyashka.gateways

import ru.nikolyashka.domain.CharacterType


interface CharacterGateway {

    fun areThereMoreCharacters(): Boolean
    fun getInitialCharacters(): List<CharacterType>
    suspend fun getCharacters(): List<CharacterType>
    suspend fun getCharactersBySearch(searchingText: String): List<CharacterType>
}