package ru.nikolyashka.gateway.models.mappers

import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateway.models.entities.CharacterEntity
import javax.inject.Inject

class CharacterEntityToModel @Inject constructor() : Mapper<CharacterType.CharacterModel, CharacterEntity> {

    override fun map(source: CharacterEntity): CharacterType.CharacterModel = CharacterType.CharacterModel(
        id = source.id.toInt(),
        name = source.name,
        imageUrl = source.imageUrl
    )
}