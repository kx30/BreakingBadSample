package ru.nikolyashka.breakingbadsample.ui.characters.adapter.models

import ru.nikolyashka.breakingbadsample.R
import ru.nikolyashka.domain.CharacterMapperToUi
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.error.ErrorType

class CharacterUiMapper(private val type: Int) : CharacterMapperToUi<CharacterUiType> {

    override fun map(character: CharacterType.CharacterModel): CharacterUiType {
        if (type == CharacterUiViewType.CHARACTER_UI_TYPE) {
            return CharacterUiType.CharacterUiModel(
                id = character.id,
                name = character.name,
                imageUrl = character.imageUrl,
                isChecked = false
            )
        } else {
            throw IllegalArgumentException("incorrect type during mapping")
        }
    }

    override fun map(characterError: CharacterType.CharacterErrorModel): CharacterUiType {
        val errorMessage = when (characterError.errorType) {
            ErrorType.Common -> R.string.something_went_wrong
            ErrorType.NetworkUnavailable -> R.string.network_unavailable
        }

        // Todo: скорее всего нужно переделать на enum
        return when (type) {
            CharacterUiViewType.CHARACTER_FULL_SCREEN_ERROR -> CharacterUiType.FullScreenError(errorMessage)
            else -> CharacterUiType.SnackBarError(errorMessage)
        }
    }
}