package ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders

import androidx.annotation.StringRes
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterFullScreenErrorBinding
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterListener
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType

class CharacterFullScreenErrorViewHolder(
    private val binding: ItemCharacterFullScreenErrorBinding,
    private val listener: CharacterListener
) : CommonCharacterViewHolder(binding) {

    override fun onBind(type: CharacterUiType) {
        val fullScreenError = type as CharacterUiType.FullScreenError

        setErrorMessage(fullScreenError.error)
        setListener()
    }

    private fun setErrorMessage(@StringRes error: Int) {
        binding.tvCharacterDetailsErrorPlaceholder.text = itemView.context.getString(error)
    }

    private fun setListener() {
        binding.btnCharacterDetailsTryAgain.setOnClickListener {
            listener.loadCharacters()
        }
    }
}