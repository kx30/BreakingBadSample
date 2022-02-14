package ru.nikolyashka.breakingbadsample.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterDiffUtilsCallback
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders.CharacterViewHolder
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders.CommonCharacterViewHolder
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType

abstract class BaseCharacterAdapter :
    ListAdapter<CharacterUiType, CommonCharacterViewHolder>(CharacterDiffUtilsCallback()) {

    override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType
    }

    override fun onBindViewHolder(holder: CommonCharacterViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun onBindViewHolder(holder: CommonCharacterViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.firstOrNull { it == CharacterDiffUtilsCallback.ON_FAVORITE_STATE_CHANGED } != null) {
            val character = currentList[position] as CharacterUiType.CharacterUiModel
            (holder as CharacterViewHolder).setFavoriteIcon(character.isFavorite)
            holder.setListener(character)
        }

        super.onBindViewHolder(holder, position, payloads)
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonCharacterViewHolder
}