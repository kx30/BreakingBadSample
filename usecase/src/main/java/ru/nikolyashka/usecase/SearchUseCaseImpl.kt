package ru.nikolyashka.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.nikolyashka.core.ErrorHandler
import ru.nikolyashka.core.NetworkManager
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateways.CharacterGateway
import ru.nikolyashka.gateways.FavoritesGateway
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(
    private val favoritesGateway: FavoritesGateway,
    private val characterGateway: CharacterGateway,
    private val networkManager: NetworkManager,
    private val errorHandler: ErrorHandler
) : SearchUseCase {

    override suspend fun getCharactersBySearch(searchingText: String): List<CharacterType> =
        if (networkManager.isInternetAvailable()) {
            invokeGlobalSearch(searchingText)
        } else {
            invokeLocalSearch(searchingText)
        }

    private suspend fun invokeGlobalSearch(searchingText: String): List<CharacterType> = kotlin.runCatching {
        val characters = withContext(Dispatchers.IO) { characterGateway.getCharactersBySearch(searchingText) }
        val favoriteCharacters = withContext(Dispatchers.IO) { favoritesGateway.getFavorites() }

        characters.map { characterType ->
            if (characterType is CharacterType.CharacterModel) {
                characterType.isFavorite = favoriteCharacters.firstOrNull { it.id == characterType.id } != null
            }
            characterType
        }
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

    private suspend fun invokeLocalSearch(searchingText: String): List<CharacterType> = kotlin.runCatching {
        withContext(Dispatchers.IO) { favoritesGateway.getFavoritesBySearch(searchingText) }
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
}