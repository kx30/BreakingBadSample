package ru.nikolyashka.usecase

import ru.nikolyashka.domain.CharacterType

class CharacterUseCaseImpl: CharacterUseCase {

    override fun getInitialData(): List<CharacterType> {
        return listOf()
    }
}