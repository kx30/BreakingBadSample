package ru.nikolyashka.gateway.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("char_id")
    val id: Int,
    val name: String,
    @SerializedName("img")
    val imageUrl: String
)