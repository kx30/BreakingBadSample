package ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders

import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import ru.nikolyashka.breakingbadsample.R
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterBinding
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterListener
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val listener: CharacterListener
) : CommonCharacterViewHolder(binding) {

    override fun onBind(type: CharacterUiType) {
        val character = type as CharacterUiType.CharacterUiModel

        setListener(character)
        setFavoriteIcon(character.isChecked)
        setName(character.name)
        setImage(character.imageUrl)
    }

    fun setListener(character: CharacterUiType.CharacterUiModel) {
        binding.flItemCharacterFavorite.setOnClickListener {
            listener.addToFavorite(character)
        }
    }

    fun setFavoriteIcon(isChecked: Boolean) {
        val drawableId = if (isChecked) {
            R.drawable.ic_favorite_filled
        } else {
            R.drawable.ic_favorite_unfilled
        }
        binding.ivItemCharacterFavorite.setImageDrawable(ContextCompat.getDrawable(itemView.context, drawableId))
    }

    private fun setName(name: String) {
        binding.tvItemCharacterName.text = name
    }

    private fun setImage(imageUrl: String) {
        Glide.with(binding.ivItemCharacter)
            .load(imageUrl)
            .into(binding.ivItemCharacter)
    }
}