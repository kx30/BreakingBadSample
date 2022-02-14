package ru.nikolyashka.gateways

import ru.nikolyashka.domain.CharacterDetailsType
import ru.nikolyashka.domain.CharacterType


interface CharacterGateway {

    fun getInitialData(): List<CharacterType>
    suspend fun getCharacters(): List<CharacterType>
}