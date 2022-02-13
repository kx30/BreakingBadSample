package ru.nikolyashka.breakingbadsample.ui.characters.adapter.models

import androidx.annotation.StringRes

sealed class CharacterUiType(val viewType: Int) {

    data class CharacterUiModel(val id: Int, val name: String, val imageUrl: String, val isFavorite: Boolean) :
        CharacterUiType(CharacterUiViewType.CHARACTER_UI_TYPE)

    data class FullScreenError(@StringRes val error: Int) :
        CharacterUiType(CharacterUiViewType.CHARACTER_FULL_SCREEN_ERROR)

    data class SnackBarError(@StringRes val error: Int) :
        CharacterUiType(CharacterUiViewType.CHARACTER_SNACKBAR_ERROR)

    object CharacterUiBottomLoader : CharacterUiType(CharacterUiViewType.CHARACTER_BOTTOM_LOADER)
    object CharacterUiCenterLoader : CharacterUiType(CharacterUiViewType.CHARACTER_CENTER_LOADER)
}