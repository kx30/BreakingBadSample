package ru.nikolyashka.breakingbadsample.ui.details.models

import ru.nikolyashka.breakingbadsample.R
import ru.nikolyashka.core.ErrorType
import ru.nikolyashka.domain.CharacterDetailsMapper
import ru.nikolyashka.domain.CharacterDetailsType
import javax.inject.Inject

class CharacterDetailsUiMapper @Inject constructor() : CharacterDetailsMapper<CharacterDetailsUiType> {

    override fun map(characterDetails: CharacterDetailsType.CharacterDetailsModel): CharacterDetailsUiType =
        CharacterDetailsUiType.CharacterDetailsUiModel(
            id = characterDetails.id,
            name = characterDetails.name,
            birthday = characterDetails.birthday,
            imageUrl = characterDetails.imageUrl,
            nickname = characterDetails.nickname
        )

    override fun map(characterDetails: CharacterDetailsType.CharacterDetailsErrorModel): CharacterDetailsUiType =
        CharacterDetailsUiType.CharacterDetailsUiError(
            error = when (characterDetails.errorType) {
                is ErrorType.NetworkUnavailable -> R.string.network_unavailable
                is ErrorType.Unknown -> R.string.something_went_wrong
            }
        )
}