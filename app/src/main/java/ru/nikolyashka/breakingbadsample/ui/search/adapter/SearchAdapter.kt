package ru.nikolyashka.breakingbadsample.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterBinding
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterFullScreenErrorBinding
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterLoaderCenterBinding
import ru.nikolyashka.breakingbadsample.databinding.ItemEmptySearchBinding
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterAdapter
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterListener
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders.CharacterCenterLoaderViewHolder
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders.CharacterFullScreenErrorViewHolder
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders.CharacterViewHolder
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders.CommonCharacterViewHolder
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiViewType
import ru.nikolyashka.breakingbadsample.ui.search.holder.SearchEmptyViewHolder

class SearchAdapter(
    private val listener: CharacterListener
) : BaseCharacterAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonCharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            CharacterUiViewType.CHARACTER_UI_TYPE -> CharacterViewHolder(
                ItemCharacterBinding.inflate(layoutInflater, parent, false), listener
            )
            CharacterUiViewType.CHARACTER_FULL_SCREEN_ERROR -> CharacterFullScreenErrorViewHolder(
                ItemCharacterFullScreenErrorBinding.inflate(layoutInflater, parent, false), listener
            )
            CharacterUiViewType.CHARACTER_CENTER_LOADER -> CharacterCenterLoaderViewHolder(
                ItemCharacterLoaderCenterBinding.inflate(layoutInflater, parent, false), listener
            )
            CharacterUiViewType.CHARACTER_EMPTY_DATA -> SearchEmptyViewHolder(
                ItemEmptySearchBinding.inflate(layoutInflater, parent, false)
            )
            else -> throw IllegalStateException()
        }
    }
}