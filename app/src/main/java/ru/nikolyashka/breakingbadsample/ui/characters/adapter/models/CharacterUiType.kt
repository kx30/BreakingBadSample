package ru.nikolyashka.breakingbadsample.ui.characters.adapter.models

import androidx.annotation.StringRes
import ru.nikolyashka.domain.CharacterType

sealed class CharacterUiType(val viewType: Int) {

    data class CharacterUiModel(val id: Int, val name: String, val imageUrl: String, val isFavorite: Boolean) :
        CharacterUiType(CharacterUiViewType.CHARACTER_UI_TYPE) {

        fun map(): CharacterType.CharacterModel = CharacterType.CharacterModel(
            id = this.id,
            name = this.name,
            imageUrl = this.imageUrl,
            isFavorite = this.isFavorite
        )
    }

    data class CharacterUiFullScreenError(@StringRes val error: Int) :
        CharacterUiType(CharacterUiViewType.CHARACTER_FULL_SCREEN_ERROR)

    data class CharacterUiBottomError(@StringRes val error: Int) :
        CharacterUiType(CharacterUiViewType.CHARACTER_BOTTOM_ERROR)

    object CharacterUiCenterLoader : CharacterUiType(CharacterUiViewType.CHARACTER_CENTER_LOADER)
    object CharacterUiEmptyData : CharacterUiType(CharacterUiViewType.CHARACTER_EMPTY_DATA)
}