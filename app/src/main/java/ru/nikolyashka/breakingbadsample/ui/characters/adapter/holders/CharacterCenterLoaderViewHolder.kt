package ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders

import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterLoaderCenterBinding
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterListener
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType

class CharacterCenterLoaderViewHolder(
    binding: ItemCharacterLoaderCenterBinding,
    private val listener: CharacterListener,
) : CommonCharacterViewHolder(binding) {

    override fun onBind(type: CharacterUiType) = listener.loadCharacters()
}