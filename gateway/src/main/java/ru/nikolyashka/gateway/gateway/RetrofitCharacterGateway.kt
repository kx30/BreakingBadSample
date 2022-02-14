package ru.nikolyashka.gateway.gateway

import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateway.Api
import ru.nikolyashka.gateway.Constants.DEFAULT_LIMIT
import ru.nikolyashka.gateway.models.responses.CharacterResponse
import ru.nikolyashka.gateways.CharacterGateway
import javax.inject.Inject

class RetrofitCharacterGateway @Inject constructor(
    private val api: Api,
) : CharacterGateway {

    private val characterList = ArrayList<CharacterResponse>()

    /***
     * Api does not send information about total items, so i think the best way its hardcode it
     * */
    private val totalPages = 7
    private var currentPage = 0


    override fun areThereMoreCharacters(): Boolean = currentPage < totalPages

    override fun getInitialCharacters(): List<CharacterType> = characterList.map { it.map() }

    override suspend fun getCharactersBySearch(searchingText: String): List<CharacterType> {
        return api.getCharactersBySearch(searchingText).map { it.map() }
    }

    override suspend fun getCharacters(): List<CharacterType> {
        if (!areThereMoreCharacters()) {
            return characterList.map { it.map() }
        }
        val characters = api.getCharacters(currentPage * DEFAULT_LIMIT)
        if (characters.isNotEmpty()) {
            characterList.addAll(characters)
            currentPage++
        }
        return characterList.map { it.map() }
    }
}