package ru.nikolyashka.gateways

import ru.nikolyashka.domain.CharacterDetailsType

interface CharacterDetailsGateway {

    suspend fun getCharacterById(id: Int): CharacterDetailsType
}