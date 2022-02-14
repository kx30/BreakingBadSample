package ru.nikolyashka.breakingbadsample.ui.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterBinding
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterBottomErrorBinding
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterFullScreenErrorBinding
import ru.nikolyashka.breakingbadsample.databinding.ItemCharacterLoaderCenterBinding
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterAdapter
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders.*
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiViewType

class CharacterAdapter(
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
            CharacterUiViewType.CHARACTER_BOTTOM_ERROR -> CharacterBottomErrorViewHolder(
                ItemCharacterBottomErrorBinding.inflate(layoutInflater, parent, false), listener
            )
            else -> throw IllegalStateException()
        }
    }
}