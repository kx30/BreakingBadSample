package ru.nikolyashka.breakingbadsample.ui.details.models

import androidx.annotation.StringRes

sealed class CharacterDetailsUiType {

    data class CharacterDetailsUiModel(
        val id: Int,
        val name: String,
        val nickname: String,
        val imageUrl: String,
        val birthday: String
    ) : CharacterDetailsUiType()

    data class CharacterDetailsUiError(@StringRes val error: Int) : CharacterDetailsUiType()
}