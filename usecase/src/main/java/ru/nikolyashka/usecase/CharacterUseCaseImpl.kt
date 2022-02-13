package ru.nikolyashka.usecase

import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.response.CharacterResponse
import ru.nikolyashka.gateways.CharacterGateway
import javax.inject.Inject

class CharacterUseCaseImpl @Inject constructor(
    private val characterGateway: CharacterGateway,
    private val responseMapper: Mapper<List<CharacterType>, List<CharacterResponse>>
) : CharacterUseCase {

    override fun getInitialData(): List<CharacterType> = responseMapper.map(characterGateway.getInitialData())

    override suspend fun getCharacters(): List<CharacterType> =
        responseMapper.map(characterGateway.getCharacters())
}