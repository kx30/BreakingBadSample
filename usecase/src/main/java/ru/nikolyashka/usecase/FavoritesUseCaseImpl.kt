package ru.nikolyashka.usecase

import ru.nikolyashka.core.ErrorHandler
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateways.FavoritesGateway
import javax.inject.Inject

class FavoritesUseCaseImpl @Inject constructor(
    private val favoritesGateway: FavoritesGateway,
    private val errorHandler: ErrorHandler
) : FavoritesUseCase {

    override suspend fun getFavoriteCharacters(): List<CharacterType> = kotlin.runCatching {
        favoritesGateway.getFavorites()
    }
        .fold(onSuccess = {
            if (it.isEmpty()) {
                arrayListOf(CharacterType.CharacterEmptyData)
            } else {
                it
            }
        }, onFailure = {
            ArrayList<CharacterType>().apply {
                add(CharacterType.CharacterErrorModel(errorHandler.defineErrorType(it)))
            }
        })

    override suspend fun changeFavoriteCharacterState(character: CharacterType.CharacterModel) {
        if (character.isFavorite) {
            favoritesGateway.removeFromFavorite(character)
        } else {
            favoritesGateway.addToFavorite(character)
        }
    }
}