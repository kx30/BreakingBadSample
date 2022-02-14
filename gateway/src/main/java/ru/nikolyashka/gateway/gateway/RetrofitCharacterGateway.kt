package ru.nikolyashka.gateway.gateway

import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateway.Api
import ru.nikolyashka.gateway.Api.Companion.DEFAULT_OFFSET
import ru.nikolyashka.gateway.models.responses.CharacterResponse
import ru.nikolyashka.gateways.CharacterGateway
import javax.inject.Inject

class RetrofitCharacterGateway @Inject constructor(
    private val api: Api,
    private val mapper: Mapper<List<@JvmSuppressWildcards CharacterType>, List<@JvmSuppressWildcards CharacterResponse>>
) : CharacterGateway {

    private val characterList = ArrayList<CharacterResponse>()

    /***
     * Api does not send information about total items, so i think the best way its hardcode it
     * */
    private val totalPages = 7
    private var currentPage = 0


    override fun areThereMoreCharacters(): Boolean = currentPage < totalPages

    override fun getInitialCharacters(): List<CharacterType> = mapper.map(characterList)

    override suspend fun getCharacters(): List<CharacterType> {
        if (!areThereMoreCharacters()) {
            return mapper.map(characterList)
        }
        val characters = api.getCharacters(currentPage * DEFAULT_OFFSET)
        if (characters.isNotEmpty()) {
            characterList.addAll(characters)
            currentPage++
        }
        return mapper.map(characterList)
    }
}