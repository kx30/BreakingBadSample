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
) {
    fun map(): CharacterDetailsType.CharacterDetailsModel =
        CharacterDetailsType.CharacterDetailsModel(
            id = this.id,
            name = this.name,
            nickname = this.nickname,
            imageUrl = this.imageUrl,
            birthday = this.birthday
        )
}