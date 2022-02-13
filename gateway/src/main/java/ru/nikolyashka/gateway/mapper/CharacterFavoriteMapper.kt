package ru.nikolyashka.gateway.mapper

import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateway.models.entities.CharacterEntity

class CharacterFavoriteMapper :
    Mapper<List<@JvmSuppressWildcards CharacterType>, List<@JvmSuppressWildcards CharacterEntity>> {

    override fun map(source: List<CharacterEntity>): List<CharacterType> = source.map {
        CharacterType.CharacterModel(
            id = it.id.toInt(),
            name = it.name,
            imageUrl = it.imageUrl
        )
    }
}