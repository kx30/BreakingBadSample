package ru.nikolyashka.gateway.gateway

import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateway.models.entities.CharacterEntity
import ru.nikolyashka.gateway.room.CharacterDao
import ru.nikolyashka.gateways.FavoritesGateway
import javax.inject.Inject

class RoomFavoritesGateway @Inject constructor(
    private val characterDao: CharacterDao
) : FavoritesGateway {

    override suspend fun getFavorites(): List<CharacterType.CharacterModel> = characterDao.getAll().map {
        it.map()
    }

    override suspend fun getFavoritesBySearch(searchingText: String): List<CharacterType.CharacterModel> =
        characterDao.getBySearch(searchingText).map {
            it.map()
        }

    override suspend fun addToFavorite(character: CharacterType.CharacterModel) =
        characterDao.insert(CharacterEntity.map(character))

    override suspend fun removeFromFavorite(character: CharacterType.CharacterModel) =
        characterDao.delete(CharacterEntity.map(character))
}