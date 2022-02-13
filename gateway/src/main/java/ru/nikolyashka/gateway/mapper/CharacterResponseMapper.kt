package ru.nikolyashka.gateway.mapper

import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateway.models.responses.CharacterResponse
import javax.inject.Inject

class CharacterResponseMapper @Inject constructor() :
    Mapper<List<@JvmSuppressWildcards CharacterType>, List<@JvmSuppressWildcards CharacterResponse>> {
    // Todo: Убрать листы из дженериков


    override fun map(source: List<CharacterResponse>): List<CharacterType> = source.map {
        CharacterType.CharacterModel(
            id = it.id,
            name = it.name,
            imageUrl = it.imageUrl
        )
    }
}