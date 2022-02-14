package ru.nikolyashka.domain

import ru.nikolyashka.core.ErrorType

sealed class CharacterType {
    abstract fun <T> map(mapper: CharacterMapperToUi<T>): T

    data class CharacterModel(
        val id: Int,
        val name: String,
        val imageUrl: String,
        val isFavorite: Boolean = false
    ) : CharacterType() {
        override fun <T> map(mapper: CharacterMapperToUi<T>): T = mapper.map(this)
    }

    data class CharacterErrorModel(val errorType: ErrorType) : CharacterType() {
        override fun <T> map(mapper: CharacterMapperToUi<T>): T = mapper.map(this)
    }
}

interface CharacterMapperToUi<T> {
    fun map(character: CharacterType.CharacterModel): T
    fun map(characterError: CharacterType.CharacterErrorModel): T
}