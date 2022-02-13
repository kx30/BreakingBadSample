package ru.nikolyashka.gateway.models.responses

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("char_id")
    val id: Int,
    val name: String,
    @SerializedName("img")
    val imageUrl: String
)