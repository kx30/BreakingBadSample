package ru.nikolyashka.gateway.models.responses

import com.google.gson.annotations.SerializedName
import ru.nikolyashka.domain.CharacterDetailsType

data class CharacterDetailsResponse(
    val id: Int,
    val name: String,
    val nickname: String,
    @SerializedName("img")
    val imageUrl: String,
    val birthday: String
) : MapperResponseToModel {
    override fun map(characterDetailsResponse: CharacterDetailsResponse): CharacterDetailsType.CharacterDetailsModel =
        CharacterDetailsType.CharacterDetailsModel(
            id = characterDetailsResponse.id,
            name = characterDetailsResponse.name,
            nickname = characterDetailsResponse.nickname,
            imageUrl = characterDetailsResponse.imageUrl,
            birthday = characterDetailsResponse.birthday
        )
}

interface MapperResponseToModel {

    fun map(characterDetailsResponse: CharacterDetailsResponse): CharacterDetailsType.CharacterDetailsModel
}