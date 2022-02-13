package ru.nikolyashka.gateways

import ru.nikolyashka.domain.CharacterType


interface CharacterGateway {

    suspend fun getCharacters(page: Int): List<CharacterType>
}