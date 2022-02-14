package ru.nikolyashka.breakingbadsample.ui.details.models

import androidx.annotation.StringRes
import ru.nikolyashka.domain.CharacterDetailsType

sealed class CharacterDetailsUiType {

    data class CharacterDetailsUiModel(
        val id: Int,
        val name: String,
        val nickname: String,
        val imageUrl: String,
        val birthday: String
    ) : CharacterDetailsUiType(), MapperToUi<CharacterDetailsType.CharacterDetailsModel> {
        override fun map(characterDetailsModel: CharacterDetailsUiModel): CharacterDetailsType.CharacterDetailsModel =
            CharacterDetailsType.CharacterDetailsModel(
                id = characterDetailsModel.id,
                name = characterDetailsModel.name,
                nickname = characterDetailsModel.nickname,
                imageUrl = characterDetailsModel.imageUrl,
                birthday = characterDetailsModel.birthday
            )
    }

    data class CharacterDetailsUiError(@StringRes val error: Int) : CharacterDetailsUiType()
}

interface MapperToUi<T> {

    fun map(characterDetailsModel: CharacterDetailsUiType.CharacterDetailsUiModel): T
}