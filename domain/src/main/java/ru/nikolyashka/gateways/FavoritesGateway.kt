package ru.nikolyashka.gateways

import ru.nikolyashka.domain.CharacterType

interface FavoritesGateway {

    suspend fun getFavorites(): List<CharacterType.CharacterModel>
    suspend fun addToFavorite(character: CharacterType.CharacterModel)
    suspend fun removeFromFavorite(id: Int)
}