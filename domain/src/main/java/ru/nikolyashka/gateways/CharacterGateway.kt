package ru.nikolyashka.gateways

import ru.nikolyashka.response.CharacterResponse


interface CharacterGateway {

    fun getInitialData(): List<CharacterResponse>
    suspend fun getCharacters(): List<CharacterResponse>
}