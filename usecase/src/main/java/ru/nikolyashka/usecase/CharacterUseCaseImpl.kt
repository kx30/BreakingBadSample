package ru.nikolyashka.usecase

import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateways.CharacterGateway
import javax.inject.Inject

class CharacterUseCaseImpl @Inject constructor(
    private val characterGateway: CharacterGateway,
) : CharacterUseCase {

    override fun getInitialData(): List<CharacterType> = characterGateway.getInitialData()

    override suspend fun getCharacters(): List<CharacterType> = characterGateway.getCharacters()
}