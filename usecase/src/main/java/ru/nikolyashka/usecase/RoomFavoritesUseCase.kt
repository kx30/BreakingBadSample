package ru.nikolyashka.usecase

import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateways.FavoritesGateway
import javax.inject.Inject

class RoomFavoritesUseCase @Inject constructor(
    private val favoritesGateway: FavoritesGateway
) : FavoritesUseCase {

    override suspend fun getFavoriteCharacters(): List<CharacterType.CharacterModel> =
        favoritesGateway.getFavorites()

    override suspend fun changeFavoriteCharacterState(character: CharacterType.CharacterModel) {
        if (character.isFavorite) {
            favoritesGateway.removeFromFavorite(character)
        } else {
            favoritesGateway.addToFavorite(character)
        }
    }
}