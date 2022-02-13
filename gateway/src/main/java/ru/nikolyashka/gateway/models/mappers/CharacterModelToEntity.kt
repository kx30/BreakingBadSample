package ru.nikolyashka.gateway.models.mappers

import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateway.models.entities.CharacterEntity
import javax.inject.Inject

class CharacterModelToEntity @Inject constructor() : Mapper<CharacterEntity, CharacterType.CharacterModel> {

    override fun map(source: CharacterType.CharacterModel): CharacterEntity = CharacterEntity(
        id = source.id.toLong(),
        name = source.name,
        imageUrl = source.imageUrl
    )
}