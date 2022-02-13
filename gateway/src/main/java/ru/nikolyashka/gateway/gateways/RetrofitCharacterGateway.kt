package ru.nikolyashka.gateway.gateways

import ru.nikolyashka.gateway.Api
import ru.nikolyashka.gateway.Api.Companion.DEFAULT_OFFSET
import ru.nikolyashka.gateways.CharacterGateway
import ru.nikolyashka.response.CharacterResponse
import javax.inject.Inject

class RetrofitCharacterGateway @Inject constructor(private val api: Api) : CharacterGateway {

    private val characterList = ArrayList<CharacterResponse>()

    /***
     * Api does not send information about total items, so i think the best way its hardcode it
     * */
    private val totalPages = 7
    private var currentPage = 0


    override fun getInitialData(): List<CharacterResponse> = emptyList()

    override suspend fun getCharacters(): List<CharacterResponse> {
        if (currentPage >= totalPages) {
            return characterList
        }
        val characters = api.getCharacters(currentPage * DEFAULT_OFFSET)
        if (characters.isNotEmpty()) {
            characterList.addAll(characters)
            currentPage++
        }
        return characterList
    }
}