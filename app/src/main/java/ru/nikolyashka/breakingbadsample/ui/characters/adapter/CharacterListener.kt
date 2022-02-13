package ru.nikolyashka.breakingbadsample.ui.characters.adapter

import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType

interface CharacterListener {

    fun addToFavorite(character: CharacterUiType.CharacterUiModel)
    fun loadCharacters()
}