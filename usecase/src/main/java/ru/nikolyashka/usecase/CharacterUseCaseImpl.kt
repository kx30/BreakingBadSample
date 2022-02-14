package ru.nikolyashka.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import ru.nikolyashka.core.ErrorHandler
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateways.CharacterGateway
import ru.nikolyashka.gateways.FavoritesGateway
import javax.inject.Inject

class CharacterUseCaseImpl @Inject constructor(
    private val characterGateway: CharacterGateway,
    private val favoriteGateway: FavoritesGateway,
    private val errorHandler: ErrorHandler
) : CharacterUseCase {

    override fun areThereMoreCharacters(): Boolean = characterGateway.areThereMoreCharacters()

    override suspend fun getCharactersBySearch(searchingText: String) = coroutineScope {
        val characters = withContext(Dispatchers.IO) { characterGateway.getCharactersBySearch(searchingText) }
        val favoriteCharacters = withContext(Dispatchers.IO) { favoriteGateway.getFavorites() }

        characters.map { characterType ->
            if (characterType is CharacterType.CharacterModel) {
                characterType.isFavorite = favoriteCharacters.firstOrNull { it.id == characterType.id } != null
            }
            characterType
        }
    }

    override suspend fun getInitialCharacters(): List<CharacterType> = coroutineScope {
        val characters = withContext(Dispatchers.IO) { characterGateway.getInitialCharacters() }
        val favoriteCharacters = withContext(Dispatchers.IO) { favoriteGateway.getFavorites() }

        characters.map { characterType ->
            if (characterType is CharacterType.CharacterModel) {
                characterType.isFavorite = favoriteCharacters.firstOrNull { it.id == characterType.id } != null
            }
            characterType
        }
    }

    override suspend fun getCharacters(): List<CharacterType> = kotlin.runCatching {
        val characters = withContext(Dispatchers.IO) { characterGateway.getCharacters() }
        val favoriteCharacters = withContext(Dispatchers.IO) { favoriteGateway.getFavorites() }

        characters.map { characterType ->
            if (characterType is CharacterType.CharacterModel) {
                characterType.isFavorite = favoriteCharacters.firstOrNull { it.id == characterType.id } != null
            }
            characterType
        }
    }
        .fold(onSuccess = {
            it
        }, onFailure = {
            ArrayList<CharacterType>().apply {
                addAll(getInitialCharacters())
                add(CharacterType.CharacterErrorModel(errorHandler.defineErrorType(it)))
            }
        })
}