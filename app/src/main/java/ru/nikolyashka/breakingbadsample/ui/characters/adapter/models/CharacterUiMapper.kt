package ru.nikolyashka.breakingbadsample.ui.characters.adapter.models

import ru.nikolyashka.breakingbadsample.R
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiViewType.CHARACTER_FULL_SCREEN_ERROR
import ru.nikolyashka.core.ErrorType
import ru.nikolyashka.domain.CharacterMapperToUi
import ru.nikolyashka.domain.CharacterType

class CharacterUiMapper(private val viewType: Int) : CharacterMapperToUi<CharacterUiType> {

    override fun map(character: CharacterType.CharacterModel): CharacterUiType {
        return CharacterUiType.CharacterUiModel(
            id = character.id,
            name = character.name,
            imageUrl = character.imageUrl,
            isFavorite = character.isFavorite
        )
    }

    override fun map(characterError: CharacterType.CharacterErrorModel): CharacterUiType {
        val errorMessage = when (characterError.errorType) {
            ErrorType.Unknown -> R.string.something_went_wrong
            ErrorType.NetworkUnavailable -> R.string.network_unavailable
        }

        return when (viewType) {
            CHARACTER_FULL_SCREEN_ERROR -> CharacterUiType.CharacterUiFullScreenError(errorMessage)
            else -> CharacterUiType.CharacterUiBottomError(errorMessage)
        }
    }

    override fun map(emptyData: CharacterType.CharacterEmptyData): CharacterUiType =
        CharacterUiType.CharacterUiEmptyData
}