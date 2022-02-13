package ru.nikolyashka.breakingbadsample.ui.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterBinding
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterLoaderBottomBinding
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterLoaderCenterBinding
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterDiffUtilsCallback.Companion.ON_FAVORITE_STATE_CHANGED
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders.CharacterBottomLoaderViewHolder
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders.CharacterCenterLoaderViewHolder
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders.CharacterViewHolder
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders.CommonCharacterViewHolder
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiViewType

class CharacterAdapter(
    private val listener: CharacterListener
) : ListAdapter<CharacterUiType, CommonCharacterViewHolder>(CharacterDiffUtilsCallback()) {

    override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType
    }

    override fun onBindViewHolder(holder: CommonCharacterViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun onBindViewHolder(holder: CommonCharacterViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.firstOrNull { it == ON_FAVORITE_STATE_CHANGED } != null) {
            val character = currentList[position] as CharacterUiType.CharacterUiModel
            (holder as CharacterViewHolder).setFavoriteIcon(character.isFavorite)
            holder.setListener(character)
        }

        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonCharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            CharacterUiViewType.CHARACTER_UI_TYPE -> CharacterViewHolder( // Todo: Вынести в экстеншн
                ItemCharacterBinding.inflate(layoutInflater, parent, false), listener
            )
            CharacterUiViewType.CHARACTER_CENTER_LOADER -> CharacterCenterLoaderViewHolder(
                ItemCharacterLoaderCenterBinding.inflate(layoutInflater, parent, false), listener
            )
            CharacterUiViewType.CHARACTER_BOTTOM_LOADER -> CharacterBottomLoaderViewHolder(
                ItemCharacterLoaderBottomBinding.inflate(layoutInflater, parent, false)
            )
            else -> throw IllegalStateException()
        }
    }
}
