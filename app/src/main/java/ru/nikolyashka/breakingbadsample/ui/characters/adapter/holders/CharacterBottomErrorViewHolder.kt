package ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders

import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterBottomErrorBinding
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterListener
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType

class CharacterBottomErrorViewHolder(
    private val binding: ItemCharacterBottomErrorBinding,
    private val listener: CharacterListener
) : CommonCharacterViewHolder(binding) {

    override fun onBind(type: CharacterUiType) {
        binding.btnCharacterBottomErrorTryAgain.setOnClickListener {
            listener.loadCharacters()
        }
    }
}