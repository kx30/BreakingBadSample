package ru.nikolyashka.gateway.gateway

import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateway.models.entities.CharacterEntity
import ru.nikolyashka.gateway.room.CharacterDao
import ru.nikolyashka.gateways.FavoritesGateway
import javax.inject.Inject

class RoomFavoritesGateway @Inject constructor(
    private val characterDao: CharacterDao,
    private val mapperFromModelToEntity: Mapper<CharacterEntity, CharacterType.CharacterModel>,
    private val mapperFromEntityToModel: Mapper<CharacterType.CharacterModel, CharacterEntity>
) : FavoritesGateway {

    override suspend fun getFavorites(): List<CharacterType.CharacterModel> = characterDao.getAll().map {
        mapperFromEntityToModel.map(it)
    }

    override suspend fun addToFavorite(character: CharacterType.CharacterModel) =
        characterDao.insert(mapperFromModelToEntity.map(character))

    override suspend fun removeFromFavorite(id: Int) {

    }
}