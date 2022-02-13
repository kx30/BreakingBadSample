package ru.nikolyashka.gateway.gateways

import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateway.Api
import ru.nikolyashka.gateway.Api.Companion.DEFAULT_OFFSET
import ru.nikolyashka.gateway.models.CharacterResponse
import ru.nikolyashka.gateways.CharacterGateway
import javax.inject.Inject

class RetrofitCharacterGateway @Inject constructor(private val api: Api) : CharacterGateway {

    private val characterList = ArrayList<CharacterResponse>()

    override suspend fun getCharacters(page: Int): List<CharacterType> {
        val characters = api.getCharacters(page * DEFAULT_OFFSET)
        if (characters.isNotEmpty()) {
            characterList.addAll(characters)
        }
        return characterList.map {
            CharacterType.CharacterModel(id = it.id, imageUrl = it.imageUrl, name = it.name)
        }
    }
}