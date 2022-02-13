package ru.nikolyashka.breakingbadsample.ui.characters.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType

class CharacterDiffUtilsCallback : DiffUtil.ItemCallback<CharacterUiType>() {

    override fun areItemsTheSame(oldItem: CharacterUiType, newItem: CharacterUiType): Boolean {
        if (oldItem is CharacterUiType.CharacterUiModel && newItem is CharacterUiType.CharacterUiModel) {
            return oldItem.id == newItem.id
        }
        return true
    }

    override fun areContentsTheSame(oldItem: CharacterUiType, newItem: CharacterUiType): Boolean {
        if (oldItem is CharacterUiType.CharacterUiModel && newItem is CharacterUiType.CharacterUiModel) {
            return oldItem.isFavorite == newItem.isFavorite
        }
        return true
    }

    override fun getChangePayload(oldItem: CharacterUiType, newItem: CharacterUiType): Any? {
        if (oldItem is CharacterUiType.CharacterUiModel && newItem is CharacterUiType.CharacterUiModel) {
            if (oldItem.isFavorite != newItem.isFavorite) {
                return ON_FAVORITE_STATE_CHANGED
            }
        }
        return super.getChangePayload(oldItem, newItem)
    }

    companion object {
        const val ON_FAVORITE_STATE_CHANGED = "OnFavoriteStateChanged"
    }
}