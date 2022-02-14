package ru.nikolyashka.domain

import ru.nikolyashka.core.ErrorType

sealed class CharacterDetailsType {
    abstract fun <T> map(mapper: CharacterDetailsMapper<T>): T

    data class CharacterDetailsModel(
        val id: Int,
        val name: String,
        val nickname: String,
        val imageUrl: String,
        val birthday: String
    ) : CharacterDetailsType() {
        override fun <T> map(mapper: CharacterDetailsMapper<T>): T = mapper.map(this)
    }

    data class CharacterDetailsErrorModel(val errorType: ErrorType) : CharacterDetailsType() {
        override fun <T> map(mapper: CharacterDetailsMapper<T>): T = mapper.map(this)
    }
}

interface CharacterDetailsMapper<T> {
    fun map(characterDetails: CharacterDetailsType.CharacterDetailsModel): T
    fun map(characterDetails: CharacterDetailsType.CharacterDetailsErrorModel): T
}