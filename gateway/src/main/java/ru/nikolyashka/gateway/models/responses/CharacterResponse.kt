package ru.nikolyashka.gateway.models.responses

import com.google.gson.annotations.SerializedName
import ru.nikolyashka.domain.CharacterType

data class CharacterResponse(
    @SerializedName("char_id")
    val id: Int,
    val name: String,
    @SerializedName("img")
    val imageUrl: String
) {
    fun map(): CharacterType.CharacterModel = CharacterType.CharacterModel(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        isFavorite = false
    )
}